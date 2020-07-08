set foreign_key_checks = 0;

drop table if exists `users`;
create table `users` (
    `id` int auto_increment,
    `username` varchar(31) not null,
    `password` varchar(31) not null,
    `email` varchar(31) not null,
    `admin` bool not null,
    primary key (`id`),
    unique key (`username`)
);

drop table if exists `shorturls`;
create table `shorturls` (
    `id` int auto_increment,
    `url` varchar(255) not null,
    `user` int not null,
    primary key (`id`),
    foreign key (`user`) references `users`(`id`)
);

set foreign_key_checks = 1;
