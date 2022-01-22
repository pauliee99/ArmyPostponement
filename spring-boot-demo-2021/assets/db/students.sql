DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `first_name` varchar(45) DEFAULT NULL,
                           `last_name` varchar(45) DEFAULT NULL,
                           `email` varchar(45) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `student` VALUES
                          (1,'David','Adams','david@hua.gr'),
                          (2,'John','Doe','john@hua.gr'),
                          (3,'Ajay','Rao','ajay@hua.gr'),
                          (4,'Mary','Public','mary@hua.gr'),
                          (5,'Maxwell','Dixon','max@hua.gr');

CREATE TABLE IF NOT EXISTS `user` (
    `username` varchar(50) NOT NULL,
    `password` varchar(100) NOT NULL,
    `enabled` tinyint(1) NOT NULL,
    PRIMARY KEY (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE IF NOT EXISTS `authorities` (
    `username` varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    UNIQUE KEY `ix_auth_username` (`username`,`authority`),
    CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `user` (`username`, `password`, `enabled`) VALUES
                                                           ('argiris', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 1),
                                                           ('root', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 1);

INSERT INTO `authorities` (`username`, `authority`) VALUES
                                                        ('root', 'ROLE_ADMIN'),
                                                        ('argiris', 'ROLE_USER');