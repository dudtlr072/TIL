package com.yys.lib;

import java.util.Scanner;

class PACKET{
    int packet_no;
    int prior_level;
    PACKET next;
}

public class Main {

    PACKET buffer;
    PACKET last_packet;

    Scanner sc;

    boolean Put_Packet_to_Buffer(PACKET pac){

        PACKET head = new PACKET();
        head.next = buffer;

        PACKET front = head.next;

        while(front.next != null) {
            if (pac.prior_level < front.next.prior_level
                    || (pac.prior_level == front.next.prior_level && pac.packet_no < front.next.packet_no)) {
                pac.next = front.next;
                front.next = pac;
                return true;
            } else {
                head.next = front.next;
                front = head.next;
            }
        }

        last_packet.next = pac;
        last_packet = pac;
        return true;
    }

    PACKET Get_Packet_from_Buffer(){

        if (buffer.next == null) return null;
        PACKET pac = buffer.next;

        buffer.next = pac.next;

        return pac;
    }

    public static void main(String[] args) {

        Main m = new Main();

        m.buffer = new PACKET();
        m.sc = new Scanner(System.in);
        m.last_packet = m.buffer;

        int N = m.sc.nextInt(); // 패킷의 수 입력
        // 패킷의 수신
        for(int i = 0; i < N; i++){
            PACKET pac = new PACKET();
            // packet_no, prior_level 입력
            pac.packet_no = m.sc.nextInt();
            pac.prior_level = m.sc.nextInt();
            // Linked-list로 패킷연결
            m.Put_Packet_to_Buffer(pac);
        }

        // 패킷 처리순서 출력
        PACKET process = null;
        while((process = m.Get_Packet_from_Buffer()) != null){
            System.out.print(process.packet_no + " ");
        }

        m.sc.close();
    }
}