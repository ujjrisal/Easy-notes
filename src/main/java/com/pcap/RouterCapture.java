package com.pcap;

import org.pcap4j.core.*;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;

import java.io.EOFException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

public class RouterCapture {

    public static void main(String[] args) throws UnknownHostException, PcapNativeException, EOFException, TimeoutException, NotOpenException {


        int timeout = 10;
        int snapLen = 65536;
        InetAddress addr = InetAddress.getByName("192.168.1.1");
        PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);



        PcapNetworkInterface.PromiscuousMode mode = PcapNetworkInterface.PromiscuousMode.PROMISCUOUS;

        PcapHandle handle = nif.openLive(snapLen, mode, timeout);
        Packet packet = handle.getNextPacketEx();
        handle.close();


        IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
        Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
        System.out.println(srcAddr);



    }

}
