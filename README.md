# moviestore-es

**enter movie details**
----
  Returns status in string format.

* **URL**

  /movies/save

* **Method:**

  'post'
    


* **Data Params**

 {
 "name":"",
 "releaseDate":"",
"language":"",
 "rating":
}
 

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** {"_index":"moviestore","_type":"movie","_id":"AVul2AoTG5Hyb0E56Tyo","_version":1,"_shards":{"total":,"successful":,"failed":},"created":}
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
 


**Search movie details by name**
----
  Returns movie details in JSON format.

* **URL**

  movies/search/:name

* **Method:**

  'get'
    
 **URL Params**

   **Required:**
 
   'name=[String]'

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:**
    
    {"took":87,"timed_out":false,"_shards":{"total":4,"successful":4,"failed":0},"hits":{"total":1,"max_score":1.4054651,"hits":[{"_index":"moviestore","_type":"movie","_id":"AVul2AoTG5Hyb0E56Tyo","_score":1.4054651,"_source":{"id":,"name":"","releaseDate":,"language":"","rating":}}]}}
    
    
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />



**Search all movie details **
----
  Returns details of all movies present in the DB in JSON format.

* **URL**

  movies/findAll

* **Method:**

  'get'
    
 **URL Params**

  none

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:**
    
    {"took":87,"timed_out":false,"_shards":{"total":4,"successful":4,"failed":0},"hits":{"total":1,"max_score":1.4054651,"hits":[{"_index":"moviestore","_type":"movie","_id":"AVul2AoTG5Hyb0E56Tyo","_score":1.4054651,"_source":{"id":,"name":"","releaseDate":,"language":"","rating":}}]}}
    
    
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />


** Elastic search Host **

search-showda-kbo36s55mzgs3s6s2ykmqu25xa.us-west-2.es.amazonaws.com




			