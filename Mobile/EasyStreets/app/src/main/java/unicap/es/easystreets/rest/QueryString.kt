package unicap.es.easystreets.rest


class QueryString constructor(vararg params: Pair<String, Any>) {
    val value: String

    init {
        val stringBuffer = StringBuffer()
        if(params.isNotEmpty()){
            stringBuffer.append("?")
            for (item in params) {
                stringBuffer.append("${item.first}=${item.second}&")
            }
        }
        value = stringBuffer.removeSuffix("&").toString();
    }
}