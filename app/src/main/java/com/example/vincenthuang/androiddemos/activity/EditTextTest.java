package com.example.vincenthuang.androiddemos.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

@Entry(desc = "测试EditText的登录界面", createTime = "2017-02-02")
public class EditTextTest extends AppCompatActivity {

    private EditText account_view, password_view;
    private ImageButton cancel_account, cancel_password, show_password_btn;
    private Button register_btn;
    private boolean isPasswordDone = false;
    private boolean isAccountDone = false;
    private boolean isShowPassword = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_test);

        account_view = (EditText) findViewById(R.id.account);
        password_view = (EditText) findViewById(R.id.password);

        cancel_account = (ImageButton) findViewById(R.id.cancel_account);
        cancel_password = (ImageButton) findViewById(R.id.cancel_password);
        show_password_btn = (ImageButton) findViewById(R.id.show_password);

        register_btn = (Button) findViewById(R.id.register);

        account_view.requestFocus();

        account_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    cancel_account.setVisibility(View.VISIBLE);
                    isAccountDone = true;
                }
                else {
                    cancel_account.setVisibility(View.INVISIBLE);
                    isAccountDone = false;
                }
                if (isAccountDone && isPasswordDone)
                    register_btn.setEnabled(true);
            }
        });
        password_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isPasswordDone = s.length() >= 6;
                if (isAccountDone && isPasswordDone)
                    register_btn.setEnabled(true);

                if (!s.toString().equals("")) {
                    cancel_password.setVisibility(View.VISIBLE);
                    show_password_btn.setVisibility(View.VISIBLE);
                }
                else {
                    cancel_password.setVisibility(View.INVISIBLE);
                    show_password_btn.setVisibility(View.INVISIBLE);
                }

                if (s.length() == 7) {
                    CharSequence password = password_view.getText();
                    //取子字符序列（从0到6-1的范围）
                    password_view.setText(password.subSequence(0, 6));
                    //将光标定位到文本框末尾，否则光标在设置文本内容后会位于最前方
                    password_view.setSelection(password_view.getText().length());
                }
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAccountDone && isPasswordDone)
                    showToast();
                else
                    showErrToast();
            }
        });

    }

    public void showToast() {
        Toast.makeText(this, "您的账号" + account_view.getText() + "注册成功", Toast.LENGTH_SHORT).show();
    }

    public void editText(View view) {
        switch (view.getId()) {
            case R.id.cancel_account:
                //获得焦点
                account_view.requestFocus();
                //如果文本框为空
                if (account_view.getText().toString().equals(""))
                    break;
                //逐个删除文本框中的字符
                int position1 = account_view.getText().length() - 1;
                account_view.setText(account_view.getText().subSequence(0, position1));
                account_view.setSelection(account_view.getText().length());
                break;
            case R.id.cancel_password:
                password_view.requestFocus();
                //如果文本框为空
                if (password_view.getText().toString().equals(""))
                    break;
                int position2 = password_view.getText().length() - 1;
                password_view.setText(password_view.getText().subSequence(0, position2));
                password_view.setSelection(password_view.getText().length());
                break;
            case R.id.show_password:
                password_view.requestFocus();
                if (isShowPassword){
                    password_view.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    show_password_btn.setImageResource(R.drawable.hide_password);
                    isShowPassword = false;
                }
                else {
                    password_view.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    show_password_btn.setImageResource(R.drawable.show_password);
                    isShowPassword = true;
                }
                break;
            default:
                break;
        }
    }

    public void forgetPassword(View view) {
        Toast.makeText(this, "再好好想想吧", Toast.LENGTH_SHORT).show();
    }

    private void showErrToast() {
        Toast.makeText(this, "帐号或密码错误，请核对账户邮箱和密码", Toast.LENGTH_SHORT).show();
    }
}
