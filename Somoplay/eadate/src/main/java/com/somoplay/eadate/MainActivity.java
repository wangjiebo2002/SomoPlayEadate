package com.somoplay.eadate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.somoplay.eadate.contact.ConversationActivity;
import com.somoplay.eadate.contact.TabFragmentContact;
import com.somoplay.somotab.ChangeColorIconWithText;
import com.somoplay.somotab.MainTabActivity;
import com.somoplay.somotab.SomoHome.TabFragmentHome;
import com.somoplay.somotab.SomoMe.TabFragmentMe;
import com.somoplay.somotab.SomoMessage.TabFragmentMessage;
import com.somoplay.somotab.SomoSearch.TabFragmentSearch;
import com.somoplay.somotab.TabFragment;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends MainTabActivity//ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        initTabView();

    }


    private void initTabView() {
        int color = 0xFF00FF00;//0xFF45C01A;

        ChangeColorIconWithText one = mTabIndicators.get(0);
        one.mText = "Home2";
        one.mColor = color;
        one.mIconBitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_menu_emoticons);
        one.refresh();

        ChangeColorIconWithText two = mTabIndicators.get(1);
        two.mText = "Search2";
        two.mColor = color;
        two.mIconBitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_menu_friendslist);
        two.refresh();

        ChangeColorIconWithText three = mTabIndicators.get(2);
        three.mText = "Message2";
        three.mColor = color;
        three.mIconBitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_menu_start_conversation);
        three.refresh();

        ChangeColorIconWithText four = mTabIndicators.get(3);
        four.mText = "Contact2";
        four.mColor = color;
        four.mIconBitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_menu_friendslist);
        four.refresh();

        ChangeColorIconWithText five = mTabIndicators.get(4);
        five.mText = "Me2";
        five.mColor = color;
        five.mIconBitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_menu_allfriends);
        five.refresh();
    }


    @Override
    protected void initTabFragment() {
        TabFragmentHome tabFragmentHome = new TabFragmentHome();
        Bundle bundleHome = new Bundle();
        bundleHome.putString(TabFragment.TITLE, "Home2");
        tabFragmentHome.setArguments(bundleHome);
        mTabs.add(tabFragmentHome);

        TabFragmentSearch tabFragmentSearch = new TabFragmentSearch();
        Bundle bundleSearch = new Bundle();


        bundleSearch.putString(TabFragment.TITLE, "Search2");
        tabFragmentSearch.setArguments(bundleSearch);
        mTabs.add(tabFragmentSearch);

        TabFragmentMessage tabFragmentMessage = new TabFragmentMessage();
        Bundle bundleMessage = new Bundle();
        bundleMessage.putString(TabFragment.TITLE, "Message2");
        tabFragmentMessage.setArguments(bundleMessage);
        mTabs.add(tabFragmentMessage);

        TabFragmentContact tabFragmentContact = new TabFragmentContact();
//        Bundle bundleContact = new Bundle();
//        bundleContact.put(TabFragmentContact.CONTEXT, "Contact2---");
//        tabFragmentContact.setArguments(bundleContact);
        mTabs.add(tabFragmentContact);

        TabFragmentMe tabFragmentMe = new TabFragmentMe();
        Bundle bundleMe = new Bundle();
        bundleMe.putString(TabFragment.TITLE, "Me2");
        tabFragmentMe.setArguments(bundleMe);
        mTabs.add(tabFragmentMe);
    }


    /**
     * 用户登录，用户登录成功，获得 cookie，将cookie 保存
     */
    private void login() {

//        final String usename = this.usename.getText().toString();
//        final String psw = this.password.getText().toString();
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
//
//                Map<String, String> requestParameter = new HashMap<String, String>();
//
//                requestParameter.put("email", usename);
//                requestParameter.put("password", psw);
//
//                String result = NetUtils.sendPostRequest("email_login", requestParameter);
//                return result;
                return null;
            }

            @Override
            protected void onPostExecute(String result) {

                getToken();
            }
        }.execute();
    }

    /**
     * 获得token
     */
    private void getToken() {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
//
//                String result = NetUtils.sendGetRequest("token");
//                return result;
                return null;
            }

            @Override
            protected void onPostExecute(String result) {

                try {
//                    if (result != null) {
//                        JSONObject object = new JSONObject(result);
//                        JSONObject jobj = object.getJSONObject("result");
//                        if (object.getInt("code") == 200) {
//                            token = jobj.getString("token");
//                            connect(token);
//                        暂时固定token--账号1234,昵称1234
                    String token = "WvGSOoF1L/DKhqvig9ns4YiV43YIDnXz4U9Txq78tU7XnnnjKLE8dL8stB+iYCGJMabcuxYxxyZhKpB3onDZGA==";
                    connect(token);
                    SharedPreferences.Editor edit = DemoContext.getInstance().getSharedPreferences().edit();
                    edit.putString("DEMO_TOKEN", token);
                    edit.apply();
//                        }
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(APP.getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */

                public void onTokenIncorrect() {

                    Log.d("LoginActivity", "--Token got error");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {

                    Log.d("LoginActivity", "--onSuccess" + userid);
                    startActivity(new Intent(MainActivity.this, ConversationActivity.class));
                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 *                  http://www.rongcloud.cn/docs/android.html#常见错误码
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
