package unicap.es.easystreets.model;

import com.google.gson.annotations.SerializedName;

import unicap.es.easystreets.R;

public enum StreetFurniture {
    @SerializedName("0")
    BusStop(R.drawable.bus_stop, "Ponto de ônibus"),                       //pontos de ônibus
    @SerializedName("1")
    TaxiPoints(R.drawable.taxi_stand, "Ponto de táxi"),                     //pontos de táxi
    @SerializedName("2")
    MailBoxes(R.drawable.mail_box, "Caixa de correio"),                      //caixas de coleta de correio
    @SerializedName("3")
    Hydrants(R.drawable.hydrant, "Hidrante"),                       //hidrantes
    @SerializedName("4")
    TelephoneNetworkCabinets(R.drawable.telephone, "Caixa de rede telefônica"),       //armários da rede telefônica
    @SerializedName("5")
    ElectricalNetworkCabinets(R.drawable.eletrical_netw_cabinet, "Caixa de rede elétrica"),      //armários da rede elétrica
    @SerializedName("6")
    Seats(R.drawable.seat, "Bancos de sentar"),                          //bancos com ou sem costas
    @SerializedName("7")
    Vase(R.drawable.vase, "Vaso"),                           //vasos
    @SerializedName("8")
    Trash(R.drawable.trash, "Cesto de lixo"),                          //lixeiras ou papeleiras
    @SerializedName("9")
    LightingPoles(R.drawable.ligh_poles, "Poste de iluminação"),                  //postes de iluminação
    @SerializedName("10")
    PowerPoles(R.drawable.power_poles, "Poste de rede elétrica"),                     //postes da rede elétrica
    @SerializedName("11")
    SignPosts(R.drawable.sign_posts, "Poste de sinalização"),                      //postes de sinalização
    @SerializedName("12")
    BikeSpot(R.drawable.bike_spot, "Parqueamento de bike"),                       //apoios ou parqueamento de bicicletas
    @SerializedName("13")
    Beacon(R.drawable.handrails, "Balisadores"),                         //divisores, guias e balizadores (fradinhos, pilones, etc)
    @SerializedName("14")
    WaterSource(R.drawable.water_source, "Bebedouros"),                    //fontes ou bebedouros
    @SerializedName("15")
    Newsstands(R.drawable.news_stand, "Banca de jornal"),                     //bancas de jornal
    @SerializedName("16")
    FlowerStands(R.drawable.flower_stand, "Banca de flores"),                   //bancas de flores ou floreiras
    @SerializedName("17")
    Clocks(R.drawable.clock, "Relógio"),                         //relógios
    @SerializedName("18")
    TablesWithBenches(R.drawable.table_with_benches, "Mesa com cadeiras"),              //mesas com bancos
    @SerializedName("19")
    Handrails(R.drawable.handrails, "Corrimãos"),                      //guardas e corrimãos
    @SerializedName("20")
    Grills(R.drawable.grill, "Grelhas"),                         //grelhas para caldeiras de árvores
    @SerializedName("21")
    ShadingStructures(R.drawable.shading_struct, "Estrutura de sombreamento"),              //estruturas de sombreamento
    @SerializedName("22")
    Dispenser(R.drawable.dispensa_degetos, "Lixo para degetos de animais"),     //dispensador de sacos para dejetos caninos
    @SerializedName("23")
    InformationMedia(R.drawable.information, "Suportes informativos"),               //suportes informativos e expositores
    @SerializedName("24")
    GymStructures(R.drawable.gym, "Estrutura de ginástica");                  //estruturas de ginástica para seniores

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

    public String toString(){
        return this.description;
    }

    public int toValue() {
        return ordinal();
    }
}
