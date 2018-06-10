package unicap.es.easystreets.model;

import com.google.gson.annotations.SerializedName;

import unicap.es.easystreets.R;

public enum StreetFurniture {
    @SerializedName("0")
    BusStop(R.drawable.bus_stop, "Bus Stop"),                       //pontos de ônibus
    @SerializedName("1")
    TaxiPoints(R.drawable.taxi_stand, "Taxi Point"),                     //pontos de táxi
    @SerializedName("2")
    MailBoxes(R.drawable.mail_box, "Mail Box"),                      //caixas de coleta de correio
    @SerializedName("3")
    Hydrants(R.drawable.hydrant, "Hydrant"),                       //hidrantes
    @SerializedName("4")
    TelephoneNetworkCabinets(R.drawable.telephone, "Telephone Network Cabinet"),       //armários da rede telefônica
    @SerializedName("5")
    ElectricalNetworkCabinets(R.drawable.eletrical_netw_cabinet, "Eletrical Network Cabinets"),      //armários da rede elétrica
    @SerializedName("6")
    Seats(R.drawable.seat, "Seat"),                          //bancos com ou sem costas
    @SerializedName("7")
    Vase(R.drawable.vase, "Vase"),                           //vasos
    @SerializedName("8")
    Trash(R.drawable.trash, "Trash"),                          //lixeiras ou papeleiras
    @SerializedName("9")
    LightingPoles(R.drawable.ligh_poles, "Lighting Poles"),                  //postes de iluminação
    @SerializedName("10")
    PowerPoles(R.drawable.power_poles, "Power Poles"),                     //postes da rede elétrica
    @SerializedName("11")
    SignPosts(R.drawable.sign_posts, "Sign Post"),                      //postes de sinalização
    @SerializedName("12")
    BikeSpot(R.drawable.bike_spot, "Bike Spot"),                       //apoios ou parqueamento de bicicletas
    @SerializedName("13")
    Beacon(R.drawable.handrails, "Beacon"),                         //divisores, guias e balizadores (fradinhos, pilones, etc)
    @SerializedName("14")
    WaterSource(R.drawable.water_source, "Water Source"),                    //fontes ou bebedouros
    @SerializedName("15")
    Newsstands(R.drawable.news_stand, "News Stand"),                     //bancas de jornal
    @SerializedName("16")
    FlowerStands(R.drawable.flower_stand, "Flower Stand"),                   //bancas de flores ou floreiras
    @SerializedName("17")
    Clocks(R.drawable.clock, "Clock"),                         //relógios
    @SerializedName("18")
    TablesWithBenches(R.drawable.table_with_benches, "Table with benches"),              //mesas com bancos
    @SerializedName("19")
    Handrails(R.drawable.handrails, "Handrails"),                      //guardas e corrimãos
    @SerializedName("20")
    Grills(R.drawable.grill, "Grill"),                         //grelhas para caldeiras de árvores
    @SerializedName("21")
    ShadingStructures(R.drawable.shading_struct, "Shading Structures"),              //estruturas de sombreamento
    @SerializedName("22")
    Dispenser(R.drawable.dispensa_degetos, "Dispenser"),     //dispensador de sacos para dejetos caninos
    @SerializedName("23")
    InformationMedia(R.drawable.information, "Information Media"),               //suportes informativos e expositores
    @SerializedName("24")
    GymStructures(R.drawable.gym, "Gym Structures");                  //estruturas de ginástica para seniores

    private int resource;
    private String description;

    StreetFurniture(int resource, String description){
        this.resource = resource;
        this.description = description;
    }

    public int getResource(){
        return this.resource;
    }

    public String getDescription(){
        return this.description;
    }
    
    public int toValue() {
        return ordinal();
    }
}
