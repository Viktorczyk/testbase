package MyApp.Model;

public enum TypeHeader {

    PZ,RW,RWZ,WZ,BO;

    public static TypeHeader chooseTypeDoc(Integer typeDocId){
        switch (typeDocId){

            case 1: return TypeHeader.PZ;
            case 2: return TypeHeader.RW;
            case 3: return TypeHeader.RWZ;
            case 4: return TypeHeader.WZ;
            case 5: return TypeHeader.BO;
        }

        return null;
    }


}
