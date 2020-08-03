INSERT INTO `event_booking`.`user` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `role`) VALUES ('1', 'Alex', 'Cumparatu', 'alex.cumparatu@m.com', 'alex.cumparatu', 'alex', 'STUDENT');


INSERT INTO `event_booking`.`user` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `role`) VALUES ('2', 'Stuart', 'Max', 'stuart.max@m.com', 'stuart.max', 'stuart', 'STUDENT');

INSERT INTO `event_booking`.`user` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `role`) VALUES ('3', 'Ben', 'Wheel', 'ben.wheel@m.com', 'ben.wheel', 'ben', 'EVENT_ORGANISER');

INSERT INTO `event_booking`.`user` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `role`) VALUES ('4', 'Mason', 'Jackson', 'mason.jackson@m.com', 'mason.jackson', 'mason', 'ADMINISTRATOR');



INSERT INTO `event_booking`.`event` (`title`, `description`, `is_online`, `location`, `start_date`, `end_date`, `constraints`, `is_booking_allowed`, `organiser_id`) VALUES
('Jack the Reaper', 'Movie in the town', 'n', 'Liverpool', '2020-03-20 23:59:59', '2020-04-02 23:59:59', '100', 'y', '2'),
('Ministry of Sound', 'Movie in the town', 'n', 'London', '2020-05-20 23:59:59', '2020-05-22 23:59:59', '100', 'y', '1');

