set foreign_key_checks = 0;

drop table if exists `users`;
create table `users` (
    `id` int auto_increment,
    `username` varchar(31) not null,
    `password` varchar(127) not null,
    `email` varchar(31) not null,
    `admin` bool not null,
    primary key (`id`),
    unique key (`username`)
);

set foreign_key_checks = 1;
