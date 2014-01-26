package org.callistasoftware.cadec2014.legacy.env;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class DataFetcher {

    private final String uriTemplate;
    private CloseableHttpClient client = HttpClients.createDefault();

    DataFetcher(String uriTemplate) {
        this.uriTemplate = uriTemplate;
    }

    /**
     * 
     * @param isbn search parameter
     * @param key api access key
     * @return
     * @throws RuntimeException on communication error
     */
    public byte[] get(String isbn, String key)  {
        return get(String.format(uriTemplate, isbn, key));
    }

    private byte[] toByteArray(InputStream content) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        drain(content, out);
        return out.toByteArray();
    }

    private void drain(InputStream content, ByteArrayOutputStream out) throws IOException {
        byte[] buffer = new byte[1024*8];
        int chunkSize;
        while ((chunkSize = content.read(buffer)) != -1) {
            out.write(buffer, 0, chunkSize);
        }
        content.close();
    }

    /**
     * Fetch content from supplied URL using GET request
     * @param url
     * @return the content
     */
    public byte[] get(String url)  {
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response;
        try {
            response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return toByteArray(response.getEntity().getContent());
            } else {
                throw new RuntimeException("Could not fetch content from " + url + " reason " + response.getStatusLine());
            }
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
