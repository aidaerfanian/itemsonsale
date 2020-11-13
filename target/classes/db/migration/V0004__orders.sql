/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  aida.erfanian
 * Created: Nov. 13, 2020
 */

CREATE TABLE `orders` (
    `id` int NOT NULL auto_increment,
    `timestamp` timestamp NOT NULL default current_timestamp ON UPDATE current_timestamp,
    `item_id` int NOT NULL,
    `user_id` int NOT NULL,
    `user_ranking` int,
    `status` varchar(255),
    `favorite` tinyint(1) NOT NULL default 0,
    FOREIGN KEY (`item_id`)
        REFERENCES items(id)
        ON DELETE CASCADE,
    FOREIGN KEY (`user_id`)
        REFERENCES users(id)
        ON DELETE CASCADE, 

    PRIMARY KEY  (`id`)
);