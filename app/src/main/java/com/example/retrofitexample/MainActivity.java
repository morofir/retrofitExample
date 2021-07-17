package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView  textView;
    private String URL = "https://jsonplaceholder.typicode.com/";
    private String URL2 = "https://http.cat/";
    Button srcButton;
    EditText editText;
    ImageView imageView;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view_result);
        srcButton = findViewById(R.id.search_btn);
        editText = findViewById(R.id.et_cats);
        imageView = findViewById(R.id.cats_iv);


        srcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = editText.getText().toString();
                Glide.with(getApplicationContext()).load(URL2+ num).override(400, 300).into(imageView);

            }
        });



        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderApi= retrofit.create(JsonPlaceHolderApi.class);
//        getPost();
//        getComment();
        createPost();

     }

     private void createPost(){
        Post post = new Post(23,"title","texttest");
//        Call<Post> call = jsonPlaceHolderApi.createPost(post);
//         Call<Post> call = jsonPlaceHolderApi.createPost(23,"new title","new Text");
         Map<String, String> fields = new HashMap<>();
         fields.put("userId","25"); //in a string
         fields.put("title","gsldjkn");
//         fields.put("text","gadfgsdhgsg");

         Call<Post> call = jsonPlaceHolderApi.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code" + response.code());
                    return;
                } else {
                    Post postResponse = response.body();
                        String content = "";
                        content += "Code: "+ response.code() + "\n"; //if ok get 200
                        content += "User Id:" + postResponse.getUserId() + "\n";
                        content += "Post Id:" + postResponse.getPostId() + "\n";
                        content += "title: " + postResponse.getTitle() + "\n";
                        content += "text:" + postResponse.getText() + "\n\n";
                        textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                textView.setText(t.getMessage());

            }
        });
     }

    private void getComment() {
//        Call<List<Comment>> call= jsonPlaceHolderApi.getComments(3);
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments("posts/7/comments"); //we can also pass the full url
//        call.execute();  this method will run in synchronous well get exceptions
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code" + response.code());
                    return;
                } else {
                    List<Comment> comments = response.body();
                    for (Comment comment : comments) {
                        String content = "";
                        content += "Id:" + comment.getPostId() + "\n";
                        content += "Post Id:" + comment.getPostId() + "\n";
                        content += "name: " + comment.getName() + "\n";
                        content += "email:" + comment.getEmail() + "\n\n";
                        textView.append(content);

                    }

                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }



    private void getPost() {
        Map<String, String> map = new HashMap<>();
        map.put("userId","4");
//        map.put("userId","7"); cant put duplicate user
        map.put("sort","id");
        map.put("order","desc");
        Call<List<Post>> call= jsonPlaceHolderApi.getPosts(map);


//        Call<List<Post>> call= jsonPlaceHolderApi.getPosts(new Integer[]{1,4,5},"id","desc"); //sort and order can be null, int cant be null,
        // we can pass Integer and then can be NULL, finlly we put array of Integer in order to have as many as ints
//        call.execute();  this method will run in synchronous well get exceptions
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textView.setText("code"+response.code());
                    return;
                }
                else{
                    List<Post> posts = response.body();
                    for(Post post:posts){
                        String content = "";
                        content += "Id:" + post.getPostId() + "\n";
                        content += "User Id:" + post.getUserId() + "\n";

                        content += "title: " + post.getTitle() + "\n";
                        content += "post:" + post.getText() + "\n\n";
                        textView.append(content);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}