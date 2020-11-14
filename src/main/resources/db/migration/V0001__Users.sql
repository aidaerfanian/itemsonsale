/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  aida.erfanian
 * Created: Nov. 12, 2020
 */


CREATE TABLE `users` (
    `user_id` int NOT NULL auto_increment,
    `username` varchar(20),
    `recommendations` varchar(255),
    PRIMARY KEY  (`user_id`)
);