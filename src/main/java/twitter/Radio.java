package twitter;

import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.FilterQuery;
import twitter4j.User;

public class Radio {
    public static void main(String[] args){
        try{
            //自動的に認証してくれる
            //（バージョン2.2.4以降はgetInstance()ではなくgetSingleton()を推奨）
            new TwitterStreamFactory();
            TwitterStream twitterstream = TwitterStreamFactory.getSingleton();
            twitterstream.addListener(new MyStatusAdapter());
            //. フィルター
            String[] track = { "ラジオ","radio" };
            FilterQuery filter = new FilterQuery();
            filter.track( track );
            twitterstream .filter( filter );
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
//コンソールに出力する
class MyStatusAdapter extends StatusAdapter {
    public void onStatus(Status status){
        //ユーザの情報を取得
        User user = status.getUser();
        //ツイートから改行記号を除去（半角スペースに変換）
        String strText = status.getText();
        strText = strText.replaceAll("\r\n"," ");
        strText = strText.replaceAll("\r"," ");
        strText = strText.replaceAll("\n"," ");
        //ツイートからタブ記号を除去（半角スペースに変換）
        strText = strText.replaceAll("\t"," ");
        //コンソールに出力する（タイムゾーン、位置情報、日時、ユーザ名、ツイート）
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(user.getTimeZone() + "\t" + status.getGeoLocation() + "\t" + status.getCreatedAt() + "\t" + user.getScreenName() + "\t" + strText);
        System.out.println("-------------------------------------------------------------------------------------");
        //System.out.println(status.getCreatedAt() + "\t" + user.getScreenName() + "\t" + strText);
    }
}
