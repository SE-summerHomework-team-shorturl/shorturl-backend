package com.example.statisticservice.service;

import com.example.sharedentity.dao.ShortUrlStatDao;
import com.example.sharedentity.entity.DailyClicksStat;
import com.example.sharedentity.entity.ShortUrlStat;
import com.example.sharedentity.util.RedirectRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
@EnableBinding(IReceiverService.class)
public class ReceiverService {
    static private final List<RedirectRecord> redirectRecords = new ArrayList<>();
    static private final int SAVE_TO_DB_THRESHOLD = 100;
    static private final int MAX_DAILY_CLICKS_STATS_SIZE = 30;

    @Autowired
    private ShortUrlStatDao shortUrlStatDao;

    @StreamListener("dpb-exchange")
    public void onReceiver(RedirectRecord redirectRecord) {
        redirectRecords.add(redirectRecord);
        if (redirectRecords.size() >= SAVE_TO_DB_THRESHOLD)
            saveToDB();
    }

    private void saveToDB() {
        Map<Long, ShortUrlStat> shortUrlStatMap = new HashMap<>();

        for (RedirectRecord redirectRecord : redirectRecords) {
            Long shortUrlId = redirectRecord.getShortUrlId();

            ShortUrlStat shortUrlStat = shortUrlStatMap.get(shortUrlId);
            if (shortUrlStat == null) {
                shortUrlStat = shortUrlStatDao.findById(shortUrlId);
                if (shortUrlStat == null)
                    shortUrlStat = new ShortUrlStat(shortUrlId);
                shortUrlStatMap.put(shortUrlId, shortUrlStat);
            }

            shortUrlStat.setTotalClicks(shortUrlStat.getTotalClicks() + 1);

            List<DailyClicksStat> dailyClicksStats = shortUrlStat.getDailyClicksStats();
            Date date = Date.from(redirectRecord.getLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            DailyClicksStat dailyClicksStat = null;
            for (DailyClicksStat elem : dailyClicksStats)
                if (elem.getDate().equals(date)) {
                    dailyClicksStat = elem;
                    break;
                }
            if (dailyClicksStat == null) {
                dailyClicksStat = new DailyClicksStat(date);
                dailyClicksStats.add(dailyClicksStat);
            }
            dailyClicksStat.setClicks(dailyClicksStat.getClicks() + 1);
        }

        for (ShortUrlStat shortUrlStat : shortUrlStatMap.values()) {
            List<DailyClicksStat> dailyClicksStats = shortUrlStat.getDailyClicksStats();
            dailyClicksStats.sort(Comparator.comparing(DailyClicksStat::getDate));
            while (dailyClicksStats.size() > MAX_DAILY_CLICKS_STATS_SIZE)
                dailyClicksStats.remove(0);
            shortUrlStatDao.save(shortUrlStat);
        }

        redirectRecords.clear();
    }
}
