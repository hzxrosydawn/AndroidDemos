package com.example.vincenthuang.androiddemos.HXML;

/**
 * 控制的消息实体
 * Created by Vincent Huang on 2017/2/2.
 */

@HXMLEntity
public class ControlEntity {

    @Head
    private String msg_id;
    @Head
    private String msg_code;
    @Head
    private String session_id;
    @Head
    private String hasbody;
    @Head
    private String result_code;
    @Head
    private String result_msg;

    // 是否是响应的实体 true -- 是
    private boolean isResponse;
    public boolean getIsResponse(){
        return isResponse;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(String msg_code) {
        this.msg_code = msg_code;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getHas_body() {
        return hasbody;
    }

    public void setHas_body(String has_body) {
        this.hasbody = has_body;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public boolean isResponse() {
        return isResponse;
    }

    public void setResponse(boolean response) {
        isResponse = response;
    }

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    @HXMLEntity
    public static class LoginControlEntity extends ControlEntity{
        @Body
        private String user;
        @Body
        private String pwd;
        @Body
        private String level;

        public LoginControlEntity(){
            setUser("admin");
            setPwd("admin");
            setLevel("1");
            setMsg_id("msg_login");
            setSession_id("0");
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }

    @HXMLEntity
    public static class PositionControlEntity extends ControlEntity{
        @Body
        private String dev_id;
        @Body
        private String speed;
        @Body
        private String cmd;

        @Body  // 毫秒数
        private String time;

        public PositionControlEntity(){
            setMsg_id("msg_dev_ptz_control");
        }

        public String getDev_id() {
            return dev_id;
        }

        public void setDev_id(String dev_id) {
            this.dev_id = dev_id;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getCmd() {
            return cmd;
        }

        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
