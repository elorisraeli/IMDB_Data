import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.widget.TextView
import com.example.imdb_data.MainActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * Created by Abhishek Panwar on 7/14/2017.
 */
class fetchData : AsyncTask<Any?, Any?, Any?>() {
    var data = ""
    var dataParsed = ""
    var singleParsed = ""

    override fun doInBackground(vararg params: Any?): Any? {
        try {
            val url = URL("https://api.myjson.com/bins/j5f6b")
            val httpURLConnection =
                url.openConnection() as HttpURLConnection
            val inputStream = httpURLConnection.inputStream
            val bufferedReader =
                BufferedReader(InputStreamReader(inputStream))
            var line: String? = ""
            while (line != null) {
                line = bufferedReader.readLine()
                data = data + line
            }
            val JA = JSONArray(data)
            for (i in 0 until JA.length()) {
                val JO = JA[i] as JSONObject
                singleParsed = """
                    Title:${JO["Title"]}
                    Year:${JO["Year"]}
                    Type:${JO["Type"]}
                    
                    """.trimIndent()
                dataParsed = """
                    $dataParsed$singleParsed
                    
                    """.trimIndent()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }

    protected fun onPostExecute(aVoid: Void?) {
        super.onPostExecute(aVoid)
        val getData = MainActivity()
        getData.getTextView()?.text = this.dataParsed
    }

}
