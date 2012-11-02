# SASHIMI?

- Specify the lifetime of your tweets with minutes accuracy
- Delete tweets in the past
- On/Off automatic deletion
- Specify the tweets to be excluded from deletion

# Why Sashimi
Sashimi is a sacred and traditional Japanese cuisine serving a fresh raw sliced fish and is thought as the most correct way to enjoy eating fish. Fishes must be extremely fresh in order to be served as Sashimi, and it has to be sliced in beautiful way; even one sloppy cut totally ruins entire Sashimi. It is not just a food on the plate. It is the art of the fragility and flow comes from its primitive magnificent appearance. 
Tweeting is like slicing Sashimi. You slice the bit of your thoughts, feelings and or sometimes even anger and transform it into ultimate versatile form of existence: that is, the words. Although the words may remain on piece of paper or surface of the rocks or even in electromagnetic field, after some time, it will die. Those words are absolutely NOT Sashimi Quality. The words has its life. In certain time, it will die as the words even if it can be seen on the paper. If it is dead, it needs to vanish: tweets need to be deleted. Under that philosophy. We provides you a handy way. Hope you like it.

# APIs
Following APIs are supported.
## Register new user 


`curl -i --data "name=sumioturk&pass=password&sashimi=15" "http://host:port/join"`
  

    HTTP/1.1 200 OK
    Content-Type: application/json;charset=UTF-8
    Content-Length: 351

    {
      "id":"3",
      "twitter_id":"",
      "name":"john",
      "pass":"password",
      "last_tweet_id":"",
      "sashimi":"15",
      "escape_term":"",
      "is_premium":0,
      "is_active":0,
      "request_token":"DLSsK50ngjYmidcZP07UOi5UxhrpsyAyS2DmPm6oEg",
      "request_token_secret":"SxkJjPQQaSENTGGbM4klMLYq5nW8VqdAJci8nUlukyA",
      "access_token":"",
      "access_token_secret":""
    }  


## Login
Note that you need a session key `2Fw/fRXNp6LfIUiKe0EbRJHeRdA=_82a83670-2511-11e2-8ddd-00270e020e4c_1351876791127` in a response header for the rest of API calls. 

`curl -i --data "name=sumioturk&pass=password" "http://host:port/login"`

    HTTP/1.1 200 OK
    Content-Type: application/json;charset=UTF-8
    Set-Cookie: key=2Fw/fRXNp6LfIUiKe0EbRJHeRdA=_82a83670-2511-11e2-8ddd-00270e020e4c_1351876791127;
    Content-Length: 27

    {"message":"You Logged In"}


## Get twitter OAuth URL
  
    curl -i \ 
    --header "Cookie: key=2Fw/fRXNp6LfIUiKe0EbRJHeRdA=_82a83670-2511-11e2-8ddd-00270e020e4c_1351876791127;" \
    --data "name=sumioturk&pass=password" \
    "http://host:port/oauth_url"

____
    HTTP/1.1 200 OK
    Content-Type: application/json;charset=UTF-8
    Content-Length: 112
 
    {
      "auth_url":"https://api.twitter.com/oauth/authorize?oauth_token=VFiyIRwClQ8PRn1JmXtsJDKhNHJhck8XaZ5iT8b8g"
    }



## Activate an account via twitter OAuth 
    
    curl -i \
    --header "Cookie: key=2Fw/fRXNp6LfIUiKe0EbRJHeRdA=_82a83670-2511-11e2-8ddd-00270e020e4c_1351876791127;" \
     --data "oauth_token=tokenhere&oauth_verifier=verifierhere" \
    "http://host:port/oauth"


## On/ff automatic deletion
    
    curl -i \
    --header "Cookie: key=2Fw/fRXNp6LfIUiKe0EbRJHeRdA=_82a83670-2511-11e2-8ddd-00270e020e4c_1351876791127;" \
    "http://host:port/toggle"

## Change lifetime of tweets (unit: min.)
 
    curl -i \
    --header "Cookie: key=2Fw/fRXNp6LfIUiKe0EbRJHeRdA=_82a83670-2511-11e2-8ddd-00270e020e4c_1351876791127;" \
    --data "sashimi=12" "http://host:port/update_sashimi"`


## Get the user's profile
    
    curl -i \
    --header "Cookie: key=2Fw/fRXNp6LfIUiKe0EbRJHeRdA=_82a83670-2511-11e2-8ddd-00270e020e4c_1351876791127;" \
    "http://host:port/get_user_profile" 

## Tweet via Sashimi
    
    curl -i \
    --header "Cookie: key=2Fw/fRXNp6LfIUiKe0EbRJHeRdA=_82a83670-2511-11e2-8ddd-00270e020e4c_1351876791127;" \
    "status=Hi, I\'m Sashimi Quality!" "http://host:port/tweet"
