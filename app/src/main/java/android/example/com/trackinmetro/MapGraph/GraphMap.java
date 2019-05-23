package android.example.com.trackinmetro.MapGraph;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.com.trackinmetro.utilities.Constants;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GraphMap {
    private int v;
    private static int no = 999;
    private ArrayList<Integer>[] adjList;
    private static ArrayList<Integer> list2 =null;
    static ArrayList<String> stationName;

    static ArrayList<GraphTime> timeMap;
    int k = 0;

    //---------------------------------------------------------------------------
    public GraphMap() {

    }

    public GraphMap(int vertices) {

        //initialise vertex count
        this.v = vertices;

        // initialise adjacency list
        initAdjList();
        //initialise time list
        timeMap = new ArrayList<>();

    }

    // utility method to initialise
    // adjacency list
    @SuppressWarnings("unchecked")
    private void initAdjList() {
        adjList = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // add edge from u to v
    public void addEdge(int u, int v) {
        // Add v to u's list.
        adjList[u].add(v);
    }

    // Prints all paths from
    // 's' to 'd'
    public void printAllPaths(int s, int d) {
        Log.d("Intent", "printAllPaths  Graph Map " + s + "==" + d);

        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> pathList = null;
        pathList = new ArrayList<>();

        //add source to path[]
        pathList.add(s);

        //Call recursive utility
        printAllPathsUtil(s, d, isVisited, pathList);
    }

    // A recursive function to print
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path
    private void printAllPathsUtil(Integer u, Integer d,
                                   boolean[] isVisited,
                                   List<Integer> localPathList) {

        // Mark the current node
        isVisited[u] = true;


        if (u.equals(d)) {
            int durTime = 0;
            for (int i = 0; i < localPathList.size() - 1; i++) {
                durTime += getTime(localPathList.get(i), localPathList.get(i + 1));
            }
//
            if (no > durTime) {
                no = durTime;
                list2 = null;
                list2 = new ArrayList<>();
                for (int i = 0; i < localPathList.size(); i++) {
                    list2.add(localPathList.get(i));
                }
            }
//            this.list.add(localPathList);
//-----------------------------------------------------------------------
            // if match found then no need to traverse more till depth
            isVisited[u] = false;
            return;
        }

        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adjList[u]) {
            if (!isVisited[i]) {
                // store current node
                // in path[]
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node
                // in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node
        isVisited[u] = false;
    }

    int getTime(int s, int d) {
        int time = 0;
        for (int i = 0; i < timeMap.size(); i++) {
            GraphTime ins = timeMap.get(i);
            if (ins.getSource() == s && ins.getDestination() == d) {
                time = ins.getDuration();
                break;
            }
        }
        return time;
    }

    void makeEdge(int s, int d) {
        addEdge(s, d);
        addEdge(d, s);
    }

    void makeDuration(int s, int d, int du) {
        timeMap.add(new GraphTime(s, d, du));
        timeMap.add(new GraphTime(d, s, du));


    }

    void setTime() {
        makeDuration(0, 1, 2);//huda City Centre to Iffco Chowk
        makeDuration(1, 2, 2);//Iffco Chowk to MG Road
        makeDuration(2, 3, 2);//MG Road to Sikanderpur
        makeDuration(3, 4, 2);//SikanderpUr to GuruDronaCharya
        makeDuration(4, 5, 3);//GuruDronacharya to Arjun Ghar
        makeDuration(5, 6, 3);//Arjun Ghar to Ghitorni
        makeDuration(6, 7, 2);//Ghitorni to sultanpur
        makeDuration(7, 8, 3);//SultanPur to chattarpur
        makeDuration(8, 9, 2);//Chhatarpur
        makeDuration(9, 10, 3);//Qutab Minar
        makeDuration(10, 11, 3);//Saket
        makeDuration(11, 12, 3);// malviya nagar 11 to hauz khas 12
        makeDuration(12, 13, 3);//Hauz Khas
        makeDuration(13, 14, 2);//Green Park
        makeDuration(14, 15, 1);//AIIMS
        makeDuration(15, 16, 3);// INA 15
        makeDuration(16, 17, 2);//JORBAGH
        makeDuration(17, 18, 2);//LOK Kalyan MArg
        makeDuration(18, 19, 1);//Udyog Bhawan
        makeDuration(19, 20, 2); //Central Secretatiate 19 to patel nagar 20
        makeDuration(20, 21, 3);// Patel Chowk
        makeDuration(21, 22, 2); //Rajiv Chowk 21
        makeDuration(22, 23, 2); //New Delhi 22
        makeDuration(23, 24, 2);//Chawri Bazar
        makeDuration(24, 25, 2);//Chandhni Chowk
        makeDuration(25, 26, 2); //Kashmere Gate 25 to civil Line 26
        makeDuration(26, 27, 2);//Civil Line
        makeDuration(27, 28, 3);//Vidhan Sabha
        makeDuration(28, 29, 2);//Viswa Vidyalaya
        makeDuration(29, 30, 2);//GTB Nagar
        makeDuration(30, 31, 2);//Model Town
        makeDuration(31, 32, 2); // Azadpur 31 to adarsh nagar 32
        makeDuration(32, 33, 2);//Adarsh Nagar
        makeDuration(33, 34, 2);//JahangiriPuri
        makeDuration(34, 35, 3);//Haiderpur Badli Mor
        makeDuration(35, 36, 3);//Rohini sector 18 to samaypur badli
        /**
         * Red Line Data
         */
        makeDuration(37, 38, 2);// rithala
        makeDuration(38, 39, 2);//rohini west
        makeDuration(39, 40, 2);//rohini east
        makeDuration(40, 41, 2);//pitam pura
        makeDuration(41, 42, 2);//kohat
        makeDuration(42, 43, 2); // netaji subhash place
        makeDuration(43, 44, 2);//keshav
        makeDuration(44, 45, 2);//kanhaiya
        makeDuration(45, 46, 2);//inder
        makeDuration(46, 47, 3);//shastri
        makeDuration(47, 48, 2);//pratap
        makeDuration(48, 49, 2);//pul
        makeDuration(49, 25, 2);// tis to kashmere
        makeDuration(25, 50, 3);//  kashmere to shashtri
        makeDuration(50, 51, 3);//shastri
        makeDuration(51, 52, 2);//seelam
        makeDuration(52, 53, 2);// welcome
        makeDuration(53, 54, 2);//shadra
        makeDuration(54, 55, 2);//mansarovar
        makeDuration(55, 56, 2);//jhilmil
        makeDuration(56, 57, 2);//dilshad
        makeDuration(57, 58, 2);//shahid
        makeDuration(58, 59, 2);//raj
        makeDuration(59, 60, 2);//major mohit
        makeDuration(60, 61, 3);//shyam
        makeDuration(61, 62, 1);//mohan
        makeDuration(62, 63, 3);//arthala
        makeDuration(63, 64, 1);//hindon to shaheed sthal new bus adda

        /**
         * Violet Line Data
         */
        makeDuration(25, 65, 2); //kashmere to lal quila
        makeDuration(65, 66, 2);//lal quila
        makeDuration(66, 67, 2);//jama masjidd
        makeDuration(67, 68, 2);//delhi gate
        makeDuration(68, 69, 2);//ito
        makeDuration(69, 70, 3);// mandi house
        makeDuration(70, 19, 2);//janpath
        makeDuration(19, 71, 3);//central secretariate
        makeDuration(71, 72, 3);// khan market
        makeDuration(72, 73, 2);//Jawahar Lal Nehru Stadium
        makeDuration(73, 74, 2);//Jangpura
        makeDuration(74, 75, 2);// lajpat nagar
        makeDuration(75, 76, 3);//Moolchand
        makeDuration(76, 77, 2);//Kailash Colony
        makeDuration(77, 78, 2);//Nehru Place
        makeDuration(78, 79, 2);// kalka ji mandir
        makeDuration(79, 80, 3);//Govind Puri
        makeDuration(80, 81, 2);//Harkesh Nagar Okhla
        makeDuration(81, 82, 3);//Jasola Apollo
        makeDuration(82, 83, 2);//Sarita Vihar
        makeDuration(83, 84, 3);//Mohan Estate
        makeDuration(84, 85, 3);//Tughlakabad
        makeDuration(85, 86, 2);//Badarpur
        makeDuration(86, 87, 3);//sarai
        makeDuration(87, 88, 2);//NHPC Chowk
        makeDuration(88, 89, 2);//Mewala Maharajapur
        makeDuration(99, 90, 2);//Sector 28
        makeDuration(90, 91, 1);//Badkal Mor
        makeDuration(91, 92, 2);//old Faridabad
        makeDuration(92, 93, 2);//Neelam Chowk Ajronada
        makeDuration(93, 94, 2);//Bata Chowk
        makeDuration(94, 95, 2);//Escort Mujesar to sant surdas
        makeDuration(95, 96, 2);//Sant Surdas to Raja Nahar Singh
        /**
         * magenta Line Data
         */
        makeDuration(97, 98, 2);// janakpuri west
        makeDuration(98, 99, 3);//Dabri Mor
        makeDuration(99, 100, 2);//Dashratpuri
        makeDuration(100, 101, 4);//palam
        makeDuration(101, 102, 2);//sadar Bazzar
        makeDuration(102, 103, 4);//Terminal 1 IGI
        makeDuration(103, 104, 2);//Shankar Vihar
        makeDuration(104, 105, 3);//Vasant Vihar
        makeDuration(105, 106, 2);//Minirka
        makeDuration(106, 107, 2);//Rk Puram
        makeDuration(107, 12, 2); //iit
        makeDuration(12, 108, 2);// hauz
        makeDuration(108, 109, 2);//panchshell
        makeDuration(109, 110, 2);//Chirag delhi
        makeDuration(110, 111, 2);// Greater Kailash
        makeDuration(111, 78, 2);//Nehru Enclave
        makeDuration(78, 112, 2);//kalaji Mandir
        makeDuration(112, 113, 2);//NSIC Okhla
        makeDuration(113, 114, 3);//Sukhdev Vihar
        makeDuration(114, 115, 2);// Jamia millia Islamia
        makeDuration(115, 116, 3);//okhla Vihar
        makeDuration(116, 117, 3);//Jasola Vihar Shaheen Bagh
        makeDuration(117, 118, 3);//Kalindi Kunj
        makeDuration(118, 119, 3);//Okhla Bird Santury to Botanical Garden
/**
 * pink Line Data
 */
        makeDuration(120, 31, 3);// majlis park
        makeDuration(31, 121, 3);//azadpur
        makeDuration(121, 42, 2);// shaleemar
        makeDuration(42, 122, 2);// Netaji subhash place
        makeDuration(122, 123, 3);//Shakur pur
        makeDuration(123, 124, 3);//panjabi bhag west
        makeDuration(124, 125, 2);//esi Basai Darapur
        makeDuration(125, 126, 3);//Rajouri Garden
        makeDuration(126, 127, 3);//MayaPuri
        makeDuration(127, 128, 3);//Naraina Vihar
        makeDuration(128, 129, 5);//Delhi Cantt.
        makeDuration(129, 130, 2);//Durgabai Deshmukh
        makeDuration(130, 131, 3);//Sri Vishweshwaraiah Moti Bagh
        makeDuration(131, 132, 2);//Bhikaji Cama place
        makeDuration(132, 15, 2);//sarojini nagar
        makeDuration(15, 133, 2);//INA
        makeDuration(133, 74, 3);//South Extension
        makeDuration(74, 134, 3);//Lajpat nagar
        makeDuration(134, 135, 3);//Vinobadpuri
        makeDuration(135, 136, 2);//AshRam
        makeDuration(136, 137, 4);//hazrat Nizamuddin
        makeDuration(137, 138, 1);//Mayur vihar-I
        //makeDuration(138, 139,);//Mayur vihar Pocket-I to trikok puri sanjay lake
        makeDuration(139, 140, 2);//Trilok Puri sanjay lake
        makeDuration(140, 141, 2);//EAST VINOD NAGAR-MAYUR VIHAR-II
        makeDuration(141, 142, 3);//MANDAWALI-WEST VINOD NAGAR
        makeDuration(142, 143, 4);//IP EXTENSION
        makeDuration(143, 144, 1);//ANAND VIHAR ISBT
        makeDuration(144, 145, 3);//KARKARDUMA
        makeDuration(145, 146, 2);//KARKARDUMA COURT
        makeDuration(146, 147, 1);//KRISHNA NAGAR
        makeDuration(147, 52, 3);//EAST AZAD NAGAR
        makeDuration(52, 148, 3);//welcome
        makeDuration(148, 149, 3);//jaffrabad
        makeDuration(149, 150, 2);//maujpur-babarpur
        makeDuration(150, 151, 2);//Gokulpuri
        makeDuration(151, 152, 1);//Johri Enclave to  Shiv vihar
        /**
         * Blue Line
         */
        makeDuration(153, 154, 3);//DWARKA SECTOR 21
        makeDuration(154, 155, 1);//DWARKA SECTOR 8
        makeDuration(155, 156, 2);//DWARKA SECTOR 9
        makeDuration(156, 157, 2);//DWARKA SECTOR 10
        makeDuration(157, 158, 2);//DWARKA SECTOR 11
        makeDuration(158, 159, 2);//DWARKA SECTOR 12
        makeDuration(159, 160, 2);//DWARKA SECTOR 13
        makeDuration(160, 161, 2);//DWARKA SECTOR 14
        makeDuration(161, 162, 3);//DWARKA
        makeDuration(162, 163, 2);//DWARKA MOR
        makeDuration(163, 164, 2);//NAWADA
        makeDuration(164, 165, 2);//UTTAM NAGAR WEST
        makeDuration(165, 97, 2);//UTTAM NAGAR EAST
        makeDuration(97, 166, 1);//JANAKPURI WEST
        makeDuration(166, 167, 2);//JANAKPURI EAST
        makeDuration(167, 168, 2);//TILAK NAGAR
        makeDuration(168, 169, 2);//SUBHASH NAGAR
        makeDuration(169, 125, 1);//TAGORE GARDEN
        makeDuration(125, 170, 2);//RAJOURI GARDEN
        makeDuration(170, 171, 2);//RAMESH NAGAR
        makeDuration(171, 172, 2);//MOTI NAGAR
        makeDuration(172, 173, 2);//KIRTI NAGAR
        makeDuration(173, 174, 3);//SHADIPUR
        makeDuration(174, 175, 1);//PATEL NAGAR
        makeDuration(175, 176, 2);//RAJENDRA PLACE
        makeDuration(176, 177, 2);//KAROL BAGH
        makeDuration(177, 178, 2);//JHANDEWALAN
        makeDuration(178, 21, 3);//RK ASHRAM MARG
        makeDuration(21, 179, 2);//RAJIV CHOWK
        makeDuration(179, 69, 2);//BARAKHAMBA ROAD
        makeDuration(69, 180, 2);//MANDI HOUSE
        makeDuration(180, 181, 2);//PRAGATI MAIDAN
        makeDuration(181, 182, 2);//INDRAPRASTHA
        makeDuration(182, 183, 3);//YAMUNA BANK
        makeDuration(183, 137, 3);//AKSHARDHAM
        makeDuration(137, 184, 2);//MAYUR VIHAR-I
        makeDuration(184, 185, 2);//MAYUR VIHAR EXTENSION
        makeDuration(185, 186, 2);//NEW ASHOK NAGAR
        makeDuration(186, 187, 2);//NOIDA SECTOR 15
        makeDuration(187, 188, 2);//NOIDA SECTOR 16
        makeDuration(188, 119, 1);//NOIDA SECTOR 18
        makeDuration(119, 189, 3);//BOTANICAL GARDEN
        makeDuration(189, 190, 2);//GOLF COURSE
        makeDuration(190, 191, 2);//NOIDA CITY CENTRE
        makeDuration(191, 192, 2);//NOIDA SECTOR 34
        makeDuration(192, 193, 2);//NOIDA SECTOR 52
        makeDuration(193, 194, 2);//NOIDA SECTOR 61
        makeDuration(194, 195, 2);//NOIDA SECTOR 59
        makeDuration(195, 196, 2);//NOIDA SECTOR 62 to NOIDA ELECTRONIC CITY
        makeDuration(182, 197, 3);//YAMUNA BANK to LAXMI NAGAR
        makeDuration(197, 198, 2);//LAXMI NAGAR
        makeDuration(198, 199, 2);//NIRMAN VIHAR
        makeDuration(199, 144, 2);//PREET VIHAR
        makeDuration(144, 143, 2);//KARKARDUMA
        makeDuration(143, 200, 2);//ANAND VIHAR ISBT
        makeDuration(200, 201, 2);//KAUSHAMBI to Vaishali

        //green Line
        makeDuration(202, 203, 2);//BRIGADIER HOSHIAR SINGH
        makeDuration(203, 204, 3);//BAHADURGARH CITY
        makeDuration(204, 205, 2);//PANDIT SHREE RAM SHARMA
        makeDuration(205, 206, 3);//TIKRI BORDER
        makeDuration(206, 207, 2);//TIKRI KALAN
        makeDuration(207, 208, 3);//GHEVRA METRO STATION
        makeDuration(208, 209, 2);//MUNDKA INDUSTRIAL AREA
        makeDuration(209, 210, 2);//MUNDKA
        makeDuration(210, 211, 2);//RAJDHANI PARK
        makeDuration(211, 212, 2);//NANGLOI RAILWAY STATION
        makeDuration(212, 213, 2);//NANGLOI
        makeDuration(213, 214, 2);//MAHARAJA SURAJMAL STADIUM
        makeDuration(214, 215, 1);//UDYOG NAGAR
        makeDuration(215, 216, 2);//PEERAGARHI
        makeDuration(216, 217, 2);//PASCHIM VIHAR WEST
        makeDuration(217, 218, 2);//PASCHIM VIHAR EAST
        makeDuration(218, 219, 2);//MADIPUR
        makeDuration(219, 220, 2);//SHIVAJI PARK
        makeDuration(220, 221, 2);//PUNJABI BAGH
        makeDuration(221, 222, 3);//ASHOK PARK MAIN to SatGuru Ram Singh Marg
        makeDuration(222, 172, 1);//SATGURU RAM SINGH MARG to Kirti nagar
        makeDuration(221, 45, 2);//ASHOK PARK MAIN to InderLok

        //orange Line
        makeDuration(153, 223, 4);// Dwarka sec21 to IGI AIRPORT
        makeDuration(223, 224, 6);//IGI AIRPORT to Delhi Airocity
        makeDuration(224, 247, 6);//DELHI AERO CITY to Dhaula Kuan
        makeDuration(247, 225, 7);//Dhaula Kuan to Shivaji stadium
        makeDuration(225, 22, 3);//SHIVAJI STADIUM to New Delhi

        //aqua Line
        makeDuration(226, 227, 3);//NOIDA SECTOR 51
        makeDuration(227, 228, 3);//NOIDA SECTOR 50
        makeDuration(228, 229, 3);//NOIDA SECTOR 76
        makeDuration(229, 230, 3);//NOIDA SECTOR 101
        makeDuration(230, 231, 3);//NOIDA SECTOR 81
        makeDuration(231, 232, 3);//NSEZ
        makeDuration(232, 233, 3);//NOIDA SECTOR 83
        makeDuration(233, 234, 3);//NOIDA SECTOR 137
        makeDuration(234, 235, 3);//NOIDA SECTOR 142
        makeDuration(235, 236, 3);//NOIDA SECTOR 143
        makeDuration(236, 237, 3);//NOIDA SECTOR 144
        makeDuration(237, 238, 3);//NOIDA SECTOR 145
        makeDuration(238, 239, 3);//NOIDA SECTOR 146
        makeDuration(239, 240, 3);//NOIDA SECTOR 147
        makeDuration(240, 241, 3);//NOIDA SECTOR 148
        makeDuration(241, 242, 3);//KNOWLEDGE PARK 2
        makeDuration(242, 243, 3);//PARI CHOWK
        makeDuration(243, 244, 3);//ALPHA 1
        makeDuration(244, 245, 3);//DELTA 1
        makeDuration(245, 246, 3);//GNOIDA OFFICE to Depot Station

    }

    public ArrayList<Integer> getMap(Context mCtx, int s, int d) {
        Log.d("Intent", "In Class  Graph Map " + s + "==" + d);

        GraphMap g = null;
        g = new GraphMap(248);

        g.makeEdge(0, 1);//huda City Centre to Iffco Chowk
        g.makeEdge(1, 2);//Iffco Chowk to MG Road
        g.makeEdge(2, 3);//MG Road to Sikanderpur
        g.makeEdge(3, 4);//SikanderpUr to GuruDronaCharya
        g.makeEdge(4, 5);//GuruDronacharya to Arjun Ghar
        g.makeEdge(5, 6);//Arjun Ghar to Ghitorni
        g.makeEdge(6, 7);//Ghitorni to sultanpur
        g.makeEdge(7, 8);//SultanPur to chattarpur
        g.makeEdge(8, 9);//Chhatarpur
        g.makeEdge(9, 10);//Qutab Minar
        g.makeEdge(10, 11);//Saket
        g.makeEdge(11, 12);// malviya nagar 11 to hauz khas 12
        g.makeEdge(12, 13);//Hauz Khas
        g.makeEdge(13, 14);//Green Park
        g.makeEdge(14, 15);//AIIMS
        g.makeEdge(15, 16);// INA 15
        g.makeEdge(16, 17);//JORBAGH
        g.makeEdge(17, 18);//LOK Kalyan MArg
        g.makeEdge(18, 19);//Udyog Bhawan
        g.makeEdge(19, 20); //Central Secretatiate 19 to patel nagar 20
        g.makeEdge(20, 21);// Patel Chowk
        g.makeEdge(21, 22); //Rajiv Chowk 21
        g.makeEdge(22, 23); //New Delhi 22
        g.makeEdge(23, 24);//Chawri Bazar
        g.makeEdge(24, 25);//Chandhni Chowk
        g.makeEdge(25, 26); //Kashmere Gate 25 to civil Line 26
        g.makeEdge(26, 27);//Civil Line
        g.makeEdge(27, 28);//Vidhan Sabha
        g.makeEdge(28, 29);//Viswa Vidyalaya
        g.makeEdge(29, 30);//GTB Nagar
        g.makeEdge(30, 31);//Model Town
        g.makeEdge(31, 32); // Azadpur 31 to adarsh nagar 32
        g.makeEdge(32, 33);//Adarsh Nagar
        g.makeEdge(33, 34);//JahangiriPuri
        g.makeEdge(34, 35);//Haiderpur Badli Mor
        g.makeEdge(35, 36);//Rohini sector 18
        /**
         * Red Line Data
         */
        g.makeEdge(37, 38);// rithala
        g.makeEdge(38, 39);//rohini west
        g.makeEdge(39, 40);//rohini east
        g.makeEdge(40, 41);//pitam pura
        g.makeEdge(41, 42);//kohat
        g.makeEdge(42, 43); // netaji subhash place
        g.makeEdge(43, 44);//keshav
        g.makeEdge(44, 45);//kanhaiya
        g.makeEdge(45, 46);//inder
        g.makeEdge(46, 47);//shastri
        g.makeEdge(47, 48);//pratap
        g.makeEdge(48, 49);//pul
        g.makeEdge(49, 25);// tis to kashmere
        g.makeEdge(25, 50);//  kashmere to shashtri
        g.makeEdge(50, 51);//shastri
        g.makeEdge(51, 52);//seelam
        g.makeEdge(52, 53);// welcome
        g.makeEdge(53, 54);//shadra
        g.makeEdge(54, 55);//mansarovar
        g.makeEdge(55, 56);//jhilmil
        g.makeEdge(56, 57);//dilshad
        g.makeEdge(57, 58);//shahid
        g.makeEdge(58, 59);//raj
        g.makeEdge(59, 60);//major mohit
        g.makeEdge(60, 61);//shyam
        g.makeEdge(61, 62);//mohan
        g.makeEdge(62, 63);//arthala
        g.makeEdge(63, 64);//hindon //

        /**
         * Violet Line Data
         */
        g.makeEdge(25, 65); //kashmere to lal quila
        g.makeEdge(65, 66);//lal quila
        g.makeEdge(66, 67);//jama masjidd
        g.makeEdge(67, 68);//delhi gate
        g.makeEdge(68, 69);//ito
        g.makeEdge(69, 70);// mandi house
        g.makeEdge(70, 19);//janpath
        g.makeEdge(19, 71);//central secretariate
        g.makeEdge(71, 72);// khan market
        g.makeEdge(72, 73);//Jawahar Lal Nehru Stadium
        g.makeEdge(73, 74);//Jangpura
        g.makeEdge(74, 75);// lajpat nagar
        g.makeEdge(75, 76);//Moolchand
        g.makeEdge(76, 77);//Kailash Colony
        g.makeEdge(77, 78);//Nehru Place
        g.makeEdge(78, 79);// kalka ji mandir
        g.makeEdge(79, 80);//Govind Puri
        g.makeEdge(80, 81);//Harkesh Nagar Okhla
        g.makeEdge(81, 82);//Jasola Apollo
        g.makeEdge(82, 83);//Sarita Vihar
        g.makeEdge(83, 84);//Mohan Estate
        g.makeEdge(84, 85);//Tughlakabad
        g.makeEdge(85, 86);//Badarpur
        g.makeEdge(86, 87);//sarai
        g.makeEdge(87, 88);//NHPC Chowk
        g.makeEdge(88, 89);//Mewala Maharajapur
        g.makeEdge(99, 90);//Sector 28
        g.makeEdge(90, 91);//Badkal Mor
        g.makeEdge(91, 92);//old Faridabad
        g.makeEdge(92, 93);//Neelam Chowk Ajronada
        g.makeEdge(93, 94);//Bata Chowk
        g.makeEdge(94, 95);//Escort Mujesar
        g.makeEdge(95, 96);//Raja
        /**
         * magenta Line Data
         */
        g.makeEdge(97, 98);// janakpuri west
        g.makeEdge(98, 99);//Dabri Mor
        g.makeEdge(99, 100);//Dashratpuri
        g.makeEdge(100, 101);//palam
        g.makeEdge(101, 102);//sadar Bazzar
        g.makeEdge(102, 103);//Terminal 1 IGI
        g.makeEdge(103, 104);//Shankar Vihar
        g.makeEdge(104, 105);//Vasant Vihar
        g.makeEdge(105, 106);//Minirka
        g.makeEdge(106, 107);//Rk Puram
        g.makeEdge(107, 12); //iit
        g.makeEdge(12, 108);// hauz
        g.makeEdge(108, 109);//panchshell
        g.makeEdge(109, 110);//Chirag delhi
        g.makeEdge(110, 111);// Greater Kailash
        g.makeEdge(111, 78);//Nehru Enclave
        g.makeEdge(78, 112);//kalaji Mandir
        g.makeEdge(112, 113);//NSIC Okhla
        g.makeEdge(113, 114);//Sukhdev Vihar
        g.makeEdge(114, 115);// Jamia millia Islamia
        g.makeEdge(115, 116);//okhla Vihar
        g.makeEdge(116, 117);//Jasola Vihar Shaheen Bagh
        g.makeEdge(117, 118);//Kalindi Kunj
        g.makeEdge(118, 119);//Okhla Bird Santury
/**
 * pink Line Data
 */
        g.makeEdge(120, 31);// majlis park
        g.makeEdge(31, 121);//azadpur
        g.makeEdge(121, 42);// shaleemar
        g.makeEdge(42, 122);// Netaji subhash place
        g.makeEdge(122, 123);//Shakur pur
        g.makeEdge(123, 124);//panjabi bhag west
        g.makeEdge(124, 125);//esi Basai Darapur
        g.makeEdge(125, 126);//Rajouri Garden
        g.makeEdge(126, 127);//MayaPuri
        g.makeEdge(127, 128);//Naraina Vihar
        g.makeEdge(128, 129);//Delhi Cantt.
        g.makeEdge(129, 130);//Durgabai Deshmukh
        g.makeEdge(130, 131);//Sri Vishweshwaraiah Moti Bagh
        g.makeEdge(131, 132);//Bhikaji kama place
        g.makeEdge(132, 15);//sarojini nagar
        g.makeEdge(15, 133);//INA
        g.makeEdge(133, 74);//South Extension
        g.makeEdge(74, 134);//Lajpat nagar
        g.makeEdge(134, 135);//Vinobadpuri
        g.makeEdge(135, 136);//AshRam
        g.makeEdge(136, 137);//hazrat Nizamuddin
        g.makeEdge(137, 138);//Mayur vihar-I
//        g.makeEdge(138, 139);//Mayur vihar Pocket-I to trilok puri
        g.makeEdge(139, 140);//Trilok Puri sanjay lake
        g.makeEdge(140, 141);//EAST VINOD NAGAR-MAYUR VIHAR-II
        g.makeEdge(141, 142);//MANDAWALI-WEST VINOD NAGAR
        g.makeEdge(142, 143);//IP EXTENSION
        g.makeEdge(143, 144);//ANAND VIHAR ISBT
        g.makeEdge(144, 145);//KARKARDUMA
        g.makeEdge(145, 146);//KARKARDUMA COURT
        g.makeEdge(146, 147);//KRISHNA NAGAR
        g.makeEdge(147, 52);//EAST AZAD NAGAR
        g.makeEdge(52, 148);//welcome
        g.makeEdge(148, 149);//jaffrabad
        g.makeEdge(149, 150);//maujpur-babarpur
        g.makeEdge(150, 151);//Gokulpuri
        g.makeEdge(151, 152);//Johri Enclave
/**
 * Blue Line
 */
        g.makeEdge(153, 154);//DWARKA SECTOR 21
        g.makeEdge(154, 155);//DWARKA SECTOR 8
        g.makeEdge(155, 156);//DWARKA SECTOR 9
        g.makeEdge(156, 157);//DWARKA SECTOR 10
        g.makeEdge(157, 158);//DWARKA SECTOR 11
        g.makeEdge(158, 159);//DWARKA SECTOR 12
        g.makeEdge(159, 160);//DWARKA SECTOR 13
        g.makeEdge(160, 161);//DWARKA SECTOR 14
        g.makeEdge(161, 162);//DWARKA
        g.makeEdge(162, 163);//DWARKA MOR
        g.makeEdge(163, 164);//NAWADA
        g.makeEdge(164, 165);//UTTAM NAGAR WEST
        g.makeEdge(165, 97);//UTTAM NAGAR EAST
        g.makeEdge(97, 166);//JANAKPURI WEST
        g.makeEdge(166, 167);//JANAKPURI EAST
        g.makeEdge(167, 168);//TILAK NAGAR
        g.makeEdge(168, 169);//SUBHASH NAGAR
        g.makeEdge(169, 125);//TAGORE GARDEN
        g.makeEdge(125, 170);//RAJOURI GARDEN
        g.makeEdge(170, 171);//RAMESH NAGAR
        g.makeEdge(171, 172);//MOTI NAGAR
        g.makeEdge(172, 173);//KIRTI NAGAR
        g.makeEdge(173, 174);//SHADIPUR
        g.makeEdge(174, 175);//PATEL NAGAR
        g.makeEdge(175, 176);//RAJENDRA PLACE
        g.makeEdge(176, 177);//KAROL BAGH
        g.makeEdge(177, 178);//JHANDEWALAN
        g.makeEdge(178, 21);//RK ASHRAM MARG
        g.makeEdge(21, 179);//RAJIV CHOWK
        g.makeEdge(179, 69);//BARAKHAMBA ROAD
        g.makeEdge(69, 180);//MANDI HOUSE
        g.makeEdge(180, 181);//PRAGATI MAIDAN
        g.makeEdge(181, 182);//INDRAPRASTHA
        g.makeEdge(182, 183);//YAMUNA BANK
        g.makeEdge(183, 137);//AKSHARDHAM
        g.makeEdge(137, 184);//MAYUR VIHAR-I
        g.makeEdge(184, 185);//MAYUR VIHAR EXTENSION
        g.makeEdge(185, 186);//NEW ASHOK NAGAR
        g.makeEdge(186, 187);//NOIDA SECTOR 15
        g.makeEdge(187, 188);//NOIDA SECTOR 16
        g.makeEdge(188, 119);//NOIDA SECTOR 18
        g.makeEdge(119, 189);//BOTANICAL GARDEN
        g.makeEdge(189, 190);//GOLF COURSE
        g.makeEdge(190, 191);//NOIDA CITY CENTRE
        g.makeEdge(191, 192);//NOIDA SECTOR 34
        g.makeEdge(192, 193);//NOIDA SECTOR 52
        g.makeEdge(193, 194);//NOIDA SECTOR 61
        g.makeEdge(194, 195);//NOIDA SECTOR 59
        g.makeEdge(195, 196);//NOIDA SECTOR 62 to NOIDA ELECTRONIC CITY
        g.makeEdge(182, 197);//YAMUNA BANK to LAXMI NAGAR
        g.makeEdge(197, 198);//LAXMI NAGAR
        g.makeEdge(198, 199);//NIRMAN VIHAR
        g.makeEdge(199, 144);//PREET VIHAR
        g.makeEdge(144, 143);//KARKARDUMA
        g.makeEdge(143, 200);//ANAND VIHAR ISBT
        g.makeEdge(200, 201);//KAUSHAMBI
        g.makeEdge(192, 226);//NODIA SECTOR 52 TO NODIA SECTOR 51
        //green Line
        g.makeEdge(202, 203);//BRIGADIER HOSHIAR SINGH
        g.makeEdge(203, 204);//BAHADURGARH CITY
        g.makeEdge(204, 205);//PANDIT SHREE RAM SHARMA
        g.makeEdge(205, 206);//TIKRI BORDER
        g.makeEdge(206, 207);//TIKRI KALAN
        g.makeEdge(207, 208);//GHEVRA METRO STATION
        g.makeEdge(208, 209);//MUNDKA INDUSTRIAL AREA
        g.makeEdge(209, 210);//MUNDKA
        g.makeEdge(210, 211);//RAJDHANI PARK
        g.makeEdge(211, 212);//NANGLOI RAILWAY STATION
        g.makeEdge(212, 213);//NANGLOI
        g.makeEdge(213, 214);//MAHARAJA SURAJMAL STADIUM
        g.makeEdge(214, 215);//UDYOG NAGAR
        g.makeEdge(215, 216);//PEERAGARHI
        g.makeEdge(216, 217);//PASCHIM VIHAR WEST
        g.makeEdge(217, 218);//PASCHIM VIHAR EAST
        g.makeEdge(218, 219);//MADIPUR
        g.makeEdge(219, 220);//SHIVAJI PARK
        g.makeEdge(220, 221);//PUNJABI BAGH
        g.makeEdge(221, 222);//ASHOK PARK MAIN
        g.makeEdge(222, 172);//SATGURU RAM SINGH MARG to Kirti nagar
        g.makeEdge(221, 45);//ASHOK PARK MAIN to InderLok

        //orange Line
        g.makeEdge(153, 223);// Dwarka sec21 toIGI AIRPORT
        g.makeEdge(223, 224);//IGI AIRPORT
        g.makeEdge(224, 247);//DELHI AERO CITY
        g.makeEdge(247, 225);//Dhaula Kuan
        g.makeEdge(225, 22);//SHIVAJI STADIUM

        //aqua Line
        g.makeEdge(226, 227);//NOIDA SECTOR 51
        g.makeEdge(227, 228);//NOIDA SECTOR 50
        g.makeEdge(228, 229);//NOIDA SECTOR 76
        g.makeEdge(229, 230);//NOIDA SECTOR 101
        g.makeEdge(230, 231);//NOIDA SECTOR 81
        g.makeEdge(231, 232);//NSEZ
        g.makeEdge(232, 233);//NOIDA SECTOR 83
        g.makeEdge(233, 234);//NOIDA SECTOR 137
        g.makeEdge(234, 235);//NOIDA SECTOR 142
        g.makeEdge(235, 236);//NOIDA SECTOR 143
        g.makeEdge(236, 237);//NOIDA SECTOR 144
        g.makeEdge(237, 238);//NOIDA SECTOR 145
        g.makeEdge(238, 239);//NOIDA SECTOR 146
        g.makeEdge(239, 240);//NOIDA SECTOR 147
        g.makeEdge(240, 241);//NOIDA SECTOR 148
        g.makeEdge(241, 242);//KNOWLEDGE PARK 2
        g.makeEdge(242, 243);//PARI CHOWK
        g.makeEdge(243, 244);//ALPHA 1
        g.makeEdge(244, 245);//DELTA 1
        g.makeEdge(245, 246);//GNOIDA OFFICE to Depot

        stationName = new ArrayList<>();

        //Yellow Line
        /*0*/
        stationName.add("HUDA CITY CENTRE");
        /*1*/
        stationName.add("IFFCO CHOWK");
        /*2*/
        stationName.add("MG ROAD");
        /*3*/
        stationName.add("SIKANDERPUR");
        /*4*/
        stationName.add("GURUDRONACHARYA");
        /*5*/
        stationName.add("ARJAN GARH");
        /*6*/
        stationName.add("GHITORNI");
        /*7*/
        stationName.add("SULTANPUR");
        /*8*/
        stationName.add("CHATTARPUR");
        /*9*/
        stationName.add("QUTAB MINAR");
        /*10*/
        stationName.add("SAKET");
        /*11*/
        stationName.add("MALVIYA NAGAR");
        /*12*/
        stationName.add("HAUZ KHAS");
        /*13*/
        stationName.add("GREEN PARK");
        /*14*/
        stationName.add("AIIMS");
        /*15*/
        stationName.add("INA");
        /*16*/
        stationName.add("JORBAGH");
        /*17*/
        stationName.add("LOK KALYAN MARG(RACE COURSE)");
        /*18*/
        stationName.add("UDYOG BHAWAN");
        /*19*/
        stationName.add("CENTRAL SECRETARIAT");
        /*20*/
        stationName.add("PATEL CHOWK");
        /*21*/
        stationName.add("RAJIV CHOWK");
        /*22*/
        stationName.add("NEW DELHI");
        /*23*/
        stationName.add("CHAWRI BAZAR");
        /*24*/
        stationName.add("CHANDHNI CHOWK");
        /*25*/
        stationName.add("KASHMERE GATE");
        /*26*/
        stationName.add("CIVIL LINES");
        /*27*/
        stationName.add("VIDHAN SABHA");
        /*28*/
        stationName.add("VISWA VIDHYALAYA");
        /*29*/
        stationName.add("GTB NAGAR");
        /*30*/
        stationName.add("MODEL TOWN");
        /*31*/
        stationName.add("AZADPUR");
        /*32*/
        stationName.add("ADARSH NAGAR");
        /*33*/
        stationName.add("JAHANGIRPURI");
        /*34*/
        stationName.add("HAIDERPUR BADLI MOR");
        /*35*/
        stationName.add("ROHINI SECTOR 18");
        /*36*/
        stationName.add("SAMAYPUR BADLI");

        //Red Line
        /*37*/
        stationName.add("RITHALA");
        /*38*/
        stationName.add("ROHINI WEST");
        /*39*/
        stationName.add("ROHINI EAST");
        /*40*/
        stationName.add("PITAMPURA");
        /*41*/
        stationName.add("KOHAT ENCLAVE");
        /*42*/
        stationName.add("NETAJI SUBHASH PLACE");
        /*43*/
        stationName.add("KESHAV PURAM");
        /*44*/
        stationName.add("KANHAIYA NAGAR");
        /*45*/
        stationName.add("INDERLOK");
        /*46*/
        stationName.add("SHASHTRI NAGAR");
        /*47*/
        stationName.add("PRATAP NAGAR");
        /*48*/
        stationName.add("PUL BANGASH");
        /*49*/
        stationName.add("TIS HAZARI");
        /*50*/
        stationName.add("SHASHTRI PARK");
        /*51*/
        stationName.add("SEELAM PUR");
        /*52*/
        stationName.add("WELCOME");
        /*53*/
        stationName.add("SHAHDARA");
        /*54*/
        stationName.add("MANSAROVA PARK");
        /*55*/
        stationName.add("JHILMIL");
        /*56*/
        stationName.add("DILSHAD GARDEN");
        /*57*/
        stationName.add("SHAHID NAGAR");
        /*58*/
        stationName.add("RAJ BAGH");
        /*59*/
        stationName.add("MAJOR MOHIT SHARMA RAJENDER NAGAR");
        /*60*/
        stationName.add("SHYAM PARK");
        /*61*/
        stationName.add("MOHAN NAGAR");
        /*62*/
        stationName.add("ARTHALA");
        /*63*/
        stationName.add("HINDON RIVER");
        /*64*/
        stationName.add("SHAHEED STHAL(NEW BUS ADDA)");

        //Violet Line
        /*65*/
        stationName.add("LAL QUILA");
        /*66*/
        stationName.add("JAMA MASJID ");
        /*67*/
        stationName.add("DELHI GATE");
        /*68*/
        stationName.add("ITO");
        /*69*/
        stationName.add("MANDI HOUSE");
        /*70*/
        stationName.add("JANPAT");
        /*71*/
        stationName.add("KHAN MARKET");
        /*72*/
        stationName.add("JAWAHARLAL NEHRU STADIUM");
        /*73*/
        stationName.add("JANGPURA");
        /*74*/
        stationName.add("LAJPAT NAGAR");
        /*75*/
        stationName.add("MOOLCHAND");
        /*76*/
        stationName.add("KAILASH COLONY");
        /*77*/
        stationName.add("NEHRU PLACE");
        /*78*/
        stationName.add("KALKAJI MANDIR");
        /*79*/
        stationName.add("GOVIND PURI");
        /*80*/
        stationName.add("HARKESH NAGAR OKHLA");
        /*81*/
        stationName.add("JASOLA APOLLO");
        /*82*/
        stationName.add("SARITA VIHAR");
        /*83*/
        stationName.add("MOHAR ESTATE");
        /*84*/
        stationName.add("TUGHLAJABAD");
        /*85*/
        stationName.add("BADARPUR");
        /*86*/
        stationName.add("SARAI");
        /*87*/
        stationName.add("NHPC CHOWK");
        /*88*/
        stationName.add("MEWALA MAHARAJPUR");
        /*89*/
        stationName.add("SECTOR 28");
        /*90*/
        stationName.add("BADKAL MOR");
        /*91*/
        stationName.add("OLD FARIDABAD");
        /*92*/
        stationName.add("NEELAM CHOWK AJRONDA");
        /*93*/
        stationName.add("BATA CHOWK");
        /*94*/
        stationName.add("ESCORTS MUJESAR");
        /*95*/
        stationName.add("SANT SURDAS(SIHI)");
        /*96*/
        stationName.add("RAJA NAHAR SINGH ");

        //magenta Line
        /*97*/
        stationName.add("JANAKPURI WEST");
        /*98*/
        stationName.add("DABRI MOR-JANAKPURI SOUTH");
        /*99*/
        stationName.add("DASHRATH PURI");
        /*100*/
        stationName.add("PALAM");
        /*101*/
        stationName.add("SADAR BAZAR CANTONMENT");
        /*102*/
        stationName.add("TERMINAL T1 IGI AIRPORT");
        /*103*/
        stationName.add("SHANKAR VIHAR”");
        /*104*/
        stationName.add("VASANT VIHAR");
        /*105*/
        stationName.add("MUNIRKA");
        /*106*/
        stationName.add("RK PURAM");
        /*107*/
        stationName.add("IIT ");
        /*108*/
        stationName.add("PANCHSHEEL PARK");
        /*109*/
        stationName.add("CHIRAG DELHI");
        /*110*/
        stationName.add("GREATER KAILASH");
        /*111*/
        stationName.add("NEHRU ENCLAVE");
        /*112*/
        stationName.add("NSIC OKHLA");
        /*113*/
        stationName.add("SUKHDEV VIHAR");
        /*114*/
        stationName.add("JAMIA MILLIA ISLAMIA");
        /*115*/
        stationName.add("OKHLA VIHAR");
        /*116*/
        stationName.add("JASOLA VIHAR SHAHEEN BAGH");
        /*117*/
        stationName.add("KALINDI KUNJ");
        /*118*/
        stationName.add("OKHLA BIRD SANTUARY");
        /*119*/
        stationName.add("BOTANICAL GARDEN  ");

        //Pink Line
        /*120*/
        stationName.add("MAJLIS PARK");
        /*121*/
        stationName.add("SHALIMAR BAGH");
        /*122*/
        stationName.add("SHAKUR PUR");
        /*123*/
        stationName.add("PUNJABI BAGH WEST");
        /*124*/
        stationName.add("ESI BASAI DARAPUR");
        /*125*/
        stationName.add("RAJOURI GARDEN");
        /*126*/
        stationName.add("MAYAPURI");
        /*127*/
        stationName.add("NARAINA VIHAR");
        /*128*/
        stationName.add("DELHI CANTT.");
        /*129*/
        stationName.add("DUGABAI DESHMUKH SOUTH CAMPUS");
        /*130*/
        stationName.add("SRI VISHWESHWARAIAH MOTI BAGH");
        /*131*/
        stationName.add("BHIKAJI KAMA PLACE");
        /*132*/
        stationName.add("SAROJINI NAGAR");
        /*133*/
        stationName.add("SOUTH EXTENSION");
        /*134*/
        stationName.add("VINOBAPURI");
        /*135*/
        stationName.add("ASHRAM");
        /*136*/
        stationName.add("HAZRAT NIZAMUDDIN");
        /*137*/
        stationName.add("MAYUR VIHAR-I");
        /*138*/
        stationName.add("MAYUR VIHAR POCKET-I");
        /*139*/
        stationName.add("TRILOK PURI SANJAY LAKE");
        /*140*/
        stationName.add("EAST VINOD NAGAR-MAYUR VIHAR-II");
        /*141*/
        stationName.add("MANDAWALI-WEST VINOD NAGAR");
        /*142*/
        stationName.add("IP EXTENSION");
        /*143*/
        stationName.add("ANAND VIHAR ISBT");
        /*144*/
        stationName.add("KARKARDUMA");
        /*145*/
        stationName.add("KARKARDUMA COURT");
        /*146*/
        stationName.add("KRISHNA NAGAR");
        /*147*/
        stationName.add("EAST AZAD NAGAR");
        /*147*/
        stationName.add("JAFFRABAD");
        /*149*/
        stationName.add("MAUJPUR-BABARPUR");
        /*150*/
        stationName.add("GOKUL PURI");
        /*151*/
        stationName.add("JOHRI ENCLAVE");
        /*152*/
        stationName.add("SHIV VIHAR");

        //Blue Line
        /*153*/
        stationName.add("DWARKA SECTOR 21");
        /*154*/
        stationName.add("DWARKA SECTOR 8");
        /*155*/
        stationName.add("DWARKA SECTOR 9");
        /*156*/
        stationName.add("DWARKA SECTOR 10");
        /*157*/
        stationName.add("DWARKA SECTOR 11");
        /*158*/
        stationName.add("DWARKA SECTOR 12");
        /*159*/
        stationName.add("DWARKA SECTOR 13");
        /*160*/
        stationName.add("DWARKA SECTOR 14");
        /*161*/
        stationName.add("DWARKA ");
        /*162*/
        stationName.add("DWARKA MOR");
        /*163*/
        stationName.add("NAWADA");
        /*164*/
        stationName.add("UTTAM NAGAR WEST");
        /*165*/
        stationName.add("UTTAM NAGAR EAST");
        /*166*/
        stationName.add("JANAKPURI EAST");
        /*167*/
        stationName.add("TILAK NAGAR");
        /*168*/
        stationName.add("SUBHASH NAGAR");
        /*169*/
        stationName.add("TAGORE GARDEN");
        /*170*/
        stationName.add("RAMESH NAGAR");
        /*171*/
        stationName.add("MOTI NAGAR");
        /*172*/
        stationName.add("KIRTI NAGAR");
        /*173*/
        stationName.add("SHADIPUR");
        /*174*/
        stationName.add("PATEL NAGAR");
        /*175*/
        stationName.add("RAJENDRA PLACE");
        /*176*/
        stationName.add("KAROL BAGH");
        /*177*/
        stationName.add("JHANDEWALAN");
        /*178*/
        stationName.add("RAMAKRISHNA ASHRAM MARG");
        /*179*/
        stationName.add("BARAKHAMBA ROAD");
        /*180*/
        stationName.add("PRAGATI MAIDAN");
        /*181*/
        stationName.add("INDRAPRASTHA");
        /*182*/
        stationName.add("YAMUNA BANK");
        /*183*/
        stationName.add("AKSHARDHAM");
        /*184*/
        stationName.add("MAYUR VIHAR EXTENSION");
        /*185*/
        stationName.add("NEW ASHOK NAGAR");
        /*186*/
        stationName.add("NOIDA SECTOR 15");
        /*187*/
        stationName.add("NOIDA SECTOR 16");
        /*188*/
        stationName.add("NOIDA SECTOR 18");
        /*189*/
        stationName.add("GOLF COURSE");
        /*190*/
        stationName.add("NOIDA CITY CENTRE");
        /*191*/
        stationName.add("NOIDA SECTOR 34");
        /*192*/
        stationName.add("NOIDA SECTOR 52");
        /*193*/
        stationName.add("NOIDA SECTOR 61");
        /*194*/
        stationName.add("NOIDA SECTOR 59");
        /*195*/
        stationName.add("NOIDA SECTOR 62");
        /*196*/
        stationName.add("NOIDA ELECTRONIC CITY");
        /*197*/
        stationName.add("LAXMI NAGAR");
        /*198*/
        stationName.add("NIRMAN VIHAR");
        /*199*/
        stationName.add("PREET VIHAR");
        /*200*/
        stationName.add("KAUSHAMBI");
        /*201*/
        stationName.add("VAISHALI");

        //green line
        /*202*/
        stationName.add("BRIGADIER HOSHIAR SINGH");
        /*203*/
        stationName.add("BAHADURGARH CITY");
        /*204*/
        stationName.add("PANDIT SHREE RAM SHARMA");
        /*205*/
        stationName.add("TIKRI BORDER");
        /*206*/
        stationName.add("TIKRI KALAN");
        /*207*/
        stationName.add("GHEVRA METRO STATION");
        /*208*/
        stationName.add("MUNDKA INDUSTRIAL AREA");
        /*209*/
        stationName.add("MUNDKA");
        /*210*/
        stationName.add("RAJDHANI PARK");
        /*211*/
        stationName.add("NANGLOI RAILWAY STATION");
        /*212*/
        stationName.add("NANGLOI");
        /*213*/
        stationName.add("MAHARAJA SURAJMAL STADIUM");
        /*214*/
        stationName.add("UDYOG NAGAR");
        /*215*/
        stationName.add("PEERAGARHI");
        /*216*/
        stationName.add("PASCHIM VIHAR WEST");
        /*217*/
        stationName.add("PASCHIM VIHAR EAST");
        /*218*/
        stationName.add("MADIPUR");
        /*219*/
        stationName.add("SHIVAJI PARK");
        /*220*/
        stationName.add("PUNJABI BAGH");
        /*221*/
        stationName.add("ASHOK PARK MAIN");
        /*222*/
        stationName.add("SATGURU RAM SINGH MARG");

        //Orange line
        /*223*/
        stationName.add("IGI AIRPORT");
        /*224*/
        stationName.add("DELHI AERO CITY");
        /*225*/
        stationName.add("SHIVAJI STADIUM");

        //aqua line
        /*226*/
        stationName.add("NOIDA SECTOR 51");
        /*227*/
        stationName.add("NOIDA SECTOR 50");
        /*228*/
        stationName.add("NOIDA SECTOR 76");
        /*229*/
        stationName.add("NOIDA SECTOR 101");
        /*230*/
        stationName.add("NOIDA SECTOR 81");
        /*231*/
        stationName.add("NSEZ");
        /*232*/
        stationName.add("NOIDA SECTOR 83");
        /*233*/
        stationName.add("NOIDA SECTOR 137");
        /*234*/
        stationName.add("NOIDA SECTOR 142");
        /*235*/
        stationName.add("NOIDA SECTOR 143");
        /*236*/
        stationName.add("NOIDA SECTOR 144");
        /*237*/
        stationName.add("NOIDA SECTOR 145");
        /*238*/
        stationName.add("NOIDA SECTOR 146");
        /*239*/
        stationName.add("NOIDA SECTOR 147");
        /*240*/
        stationName.add("NOIDA SECTOR 148");
        /*241*/
        stationName.add("KNOWLEDGE PARK 2");
        /*242*/
        stationName.add("PARI CHOWK");
        /*243*/
        stationName.add("ALPHA 1");
        /*244*/
        stationName.add("DELTA 1");
        /*245*/
        stationName.add("GNOIDA OFFICE");
        /*246*/
        stationName.add("DEPOT");

        /**
         * Add New Station name
         */
        //orange line
        /*247*/
        stationName.add("Dhaula Kuan");

        /**
         * Function For setting duration between station
         */
        setTime();
        // arbitrary source
//        int s = 37;
//
//        // arbitrary destination
//        int d = 0;
        Log.d("Intent", "In Class  Graph Map " + s + "==" + d);

        g.printAllPaths(s, d);
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Constants.SHARED_PREF_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.TRAVELLING_TIME, no);
        editor.apply();
        Log.d("Intent", "In Graph map" + list2.toString());
        return list2;
    }

}
