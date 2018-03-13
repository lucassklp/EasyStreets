package unicap.es.easystreets.rest

import com.android.volley.VolleyError

class JsonError constructor(val data: ErrorData) : VolleyError()