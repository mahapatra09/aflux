

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright [2019] [Tanmaya Mahapatra]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.tum.in.controller.bigdata_apis;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.tum.in.json_responses.PigProxyIn;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;


/**
 * Created by mahapatr on 24/09/16.
 */
@RestController
@RequestMapping("/bigdata/pig/qrygraph")
public class PigQryGraphApis {

    static Logger log = Logger.getLogger(PigQryGraphApis.class.getName());

    @RequestMapping(value = "/proxy", method = RequestMethod.OPTIONS)
    public ResponseEntity proxyOptions() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "POST");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/proxy", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity<String> proxyPost(@RequestBody PigProxyIn pigProxyIn) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Access-Control-Allow-Origin", "*");
        try {
            log.info("[pigProxyIn.getQueryCommand():" + (pigProxyIn == null ? "ISNULL" : pigProxyIn.getQueryCommand()) + "]");

            String response = getText(pigProxyIn, pigProxyIn.getQueryCommand());
            return new ResponseEntity(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getText(PigProxyIn pigProxyIn, String url) throws Exception {
        String urlParameters  = "email=" + pigProxyIn.getQryGraphUsername() + "&password=" + pigProxyIn.getQryGraphPassword();
        byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
        int    postDataLength = postData.length;
        URL websiteLogin = new URL("http://" + pigProxyIn.getQryGraphHost() + ":" + pigProxyIn.getQryGraphPort() + "/login");
        HttpURLConnection conn = (HttpURLConnection) websiteLogin.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        conn.setUseCaches(false);
        try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
            wr.write( postData );
            wr.flush();
        }

//        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//
//        writer.write();
//        writer.flush();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();

        log.warn("########## Set-Cookie: " + conn.getHeaderField("Set-Cookie"));
        String cookie = conn.getHeaderField("Set-Cookie");

//        java.net.CookieManager msCookieManager = new java.net.CookieManager();
//        Map<String, List<String>> headerFields = conn.getHeaderFields();
//        List<String> cookiesHeader = headerFields.get("Set-Cookie");
//        if (cookiesHeader != null) {
//            for (String cookie : cookiesHeader) {
//                msCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
//            }
//        }

        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        connection.setRequestProperty("Cookie", cookie);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine + "\r\n");
        if (response.length() > 2) {
            response.delete(response.length() - 2, response.length());
        }

        in.close();

        return response.toString();
    }

}
