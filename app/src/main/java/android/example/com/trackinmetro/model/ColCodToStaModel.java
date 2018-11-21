package android.example.com.trackinmetro.model;

import java.util.ArrayList;

public class ColCodToStaModel {
    private String colorCode;
    ArrayList<StationNoNum> mdata;

    public ColCodToStaModel(String colorCode, ArrayList<StationNoNum> mdata) {
        this.colorCode = colorCode;
        this.mdata = mdata;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public ArrayList<StationNoNum> getMdata() {
        return mdata;
    }

    public void setMdata(ArrayList<StationNoNum> mdata) {
        this.mdata = mdata;
    }

    /**
     * InnerClass For Station Name and Code
     */
    private class StationNoNum {
        private String no;
        private String name;

        public StationNoNum(String no, String name) {
            this.no = no;
            this.name = name;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
