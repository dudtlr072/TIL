package com.yys.lib;

import java.util.Scanner;

class PACKET{
    int packet_no;
    int prior_level;
    PACKET next;
    void print() {
        System.out.println("packet_no: " + packet_no + " / prior_level: " + prior_level + " / next: " + next);
    }
}

public class Main {

    PACKET buffer;
    PACKET last_packet;

    Scanner sc;

    boolean Put_Packet_to_Buffer(PACKET pac){

        PACKET head = new PACKET();
        head.next = buffer;

//        if (inner_buf.next == null) {
//            last_packet.next = pac;
//            last_packet = pac;
//            return true;
//        }

        while(head.next.next != null) {
            if (pac.prior_level < head.next.prior_level
                    || (pac.prior_level == head.next.prior_level && pac.packet_no < head.next.packet_no)) {
                System.out.println("(" + pac.packet_no + ", " + pac.prior_level + ") / (" + head.next.packet_no + ", " + head.next.prior_level + ")");
                pac.next = head.next;
                head.next = pac;
                System.out.println("----------------");
                return true;
            } else {
                PACKET temp = head.next;
                head.next = temp.next;
            }
        }

        last_packet.next = pac;
        last_packet = pac;
        System.out.println("----------------");
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

        m.buffer.print();
        System.out.println("buffer: " + m.buffer);
        System.out.println("last: " + m.last_packet);

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