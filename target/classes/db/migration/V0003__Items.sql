/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  aida.erfanian
 * Created: Nov. 13, 2020
 */

CREATE TABLE `items` (
    `item_id` int NOT NULL auto_increment,
    `name` varchar(20),
    `description` varchar(255),
    `category` varchar(255) NOT NULL default 'other',
    `price` int,
    `on_sale` tinyint(1) NOT NULL default 0,
    PRIMARY KEY  (`item_id`)
);