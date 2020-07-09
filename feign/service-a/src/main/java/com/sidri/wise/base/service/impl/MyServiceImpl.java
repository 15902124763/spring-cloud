package com.sidri.wise.base.service.impl;

import com.sidri.wise.base.admin.api.OpenApi;
import com.sidri.wise.base.admin.bo.open.TokenBo;
import com.sidri.wise.base.service.MyService;
import com.sidri.wise.common.beans.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author admin
 */
@Service
public class MyServiceImpl implements MyService {

    @Autowired
    OpenApi openApi;

    @Override
    public void test(){
        TokenBo tokenBo = new TokenBo();
        tokenBo.setJwtToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJ1c2VyQml6SWQiOiIyMDIwMDMwNkFSSWR3WFBPYmIiLCJ1c2VyTmFtZSI6Iueuoee9kSIsInVzZXJObyI6IjAwMiIsIm5pY2tOYW1lIjoi5bCP5LiJIiwiY2VsbFBob25lIjoiMTMwMDAwMDAwMDIiLCJtYWlsIjoiYmNkQHNpZHJpLmNvbSIsIm9yZ0lkcyI6WyJmYWFjNzc4MzlhNzI0OGVlYTgxZThlMWVhNTlhODNjYSIsImFjZjA4ODAxNTM3NDRkNjc5NjE4Y2JlMTlkMTE1OTliIl0sImdyb3VwSWRzIjpbIkc1OTU5NDc2YzdkZmM0MDljYTQ3MzczYjQ5MzliZmIzZCJdLCJwZXJtaXNzaW9ucyI6WyJSMDI5MDBjYzQ0NTMzNDYyYTlkMjNiZDNjYjE2YWY2NDMiLCJSMTYyNWVjZmUwYjU1NDMyNTgyNTZjOGJiYjg3NjgwYjAiLCJSZTY5ZTFmZTVmZDVkNDcyNGJhMDBkM2I1OWZkMmU3YTMiLCJSODBiODkyYTcwZGI2NDIwNjg1ZDM3MzRjYmQ3ZWMyZDAiXSwiYXBwUmVnaXN0cnlJZHMiOltdLCJleHAiOjE1ODgwOTczOTN9.7DO1_ftnUDWLkdAQhccvVP3MLL6OTgQ4mu4yQALlKTIcz3X_S6TkJFG7Cp9folaw3cKErdgxlu_rbvrmi8mkqg");
        Result<Boolean> result1 = openApi.checkToken(tokenBo);
        System.out.println(result1);
    }
}
