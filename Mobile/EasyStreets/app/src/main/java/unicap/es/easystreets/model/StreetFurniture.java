package unicap.es.easystreets.model;

import com.google.gson.annotations.SerializedName;

import unicap.es.easystreets.R;

public enum StreetFurniture {
    @SerializedName("0")
    BusStop(R.drawable.bus_stop),                       //pontos de ônibus
    @SerializedName("1")
    TaxiPoints(R.drawable.bus_stop),                     //pontos de táxi
    @SerializedName("2")
    MailBoxes(R.drawable.bus_stop),                      //caixas de coleta de correio
    @SerializedName("3")
    Hydrants(R.drawable.bus_stop),                       //hidrantes
    @SerializedName("4")
    TelephoneNetworkCabinets(R.drawable.bus_stop),       //armários da rede telefônica
    @SerializedName("5")
    ElectricalNetworkCabinets(R.drawable.bus_stop),      //armários da rede elétrica
    @SerializedName("6")
    Seats(R.drawable.bus_stop),                          //bancos com ou sem costas
    @SerializedName("7")
    Vase(R.drawable.bus_stop),                           //vasos
    @SerializedName("8")
    Trash(R.drawable.bus_stop),                          //lixeiras ou papeleiras
    @SerializedName("9")
    LightingPoles(R.drawable.bus_stop),                  //postes de iluminação
    @SerializedName("10")
    PowerPoles(R.drawable.bus_stop),                     //postes da rede elétrica
    @SerializedName("11")
    SignPosts(R.drawable.bus_stop),                      //postes de sinalização
    @SerializedName("12")
    BikeSpot(R.drawable.bus_stop),                       //apoios ou parqueamento de bicicletas
    @SerializedName("13")
    Beacon(R.drawable.bus_stop),                         //divisores, guias e balizadores (fradinhos, pilones, etc)
    @SerializedName("14")
    WaterSource(R.drawable.bus_stop),                    //fontes ou bebedouros
    @SerializedName("15")
    Newsstands(R.drawable.bus_stop),                     //bancas de jornal
    @SerializedName("16")
    FlowerStands(R.drawable.bus_stop),                   //bancas de flores ou floreiras
    @SerializedName("17")
    Clocks(R.drawable.bus_stop),                         //relógios
    @SerializedName("18")
    TablesWithBenches(R.drawable.bus_stop),              //mesas com bancos
    @SerializedName("19")
    Handrails(R.drawable.bus_stop),                      //guardas e corrimãos
    @SerializedName("20")
    Grills(R.drawable.bus_stop),                         //grelhas para caldeiras de árvores
    @SerializedName("21")
    ShadingStructures(R.drawable.bus_stop),              //estruturas de sombreamento
    @SerializedName("22")
    Dispenser(R.drawable.bus_stop),                      //dispensador de sacos para dejetos caninos
    @SerializedName("23")
    InformationMedia(R.drawable.bus_stop),               //suportes informativos e expositores
    @SerializedName("24")
    GymStructures(R.drawable.bus_stop);                  //estruturas de ginástica para seniores

    private int resource;

    StreetFurniture(int resource){
        this.resource = resource;
    }

    public int getResource(){
        return this.resource;
    }

    public int toValue() {
        return ordinal();
    }


}
