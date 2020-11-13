/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.stereotype.Component;

/**
 *
 * @author aida.erfanian
 */
@Component
public class Helper {

    public Helper() {
    }
    
    /**
     *
     * @param domainName
     * @return IP address
     * @throws java.net.UnknownHostException
     */
    public String getIPAddressOfDomain(String domainName) throws UnknownHostException {
       return InetAddress.getByName(domainName).toString().substring(domainName.length());
    }
}
