package tsi.pdmsf.memorygame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;

    public static String EXTRA_URL = "key.EXTRA_URL";
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        setupActionBar();

        initComponent();
        setupWebView();

        loadWebFromURL();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initComponent() {
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.webViewProgress);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        webView.setBackgroundColor(Color.TRANSPARENT);

        ActionBar actionBar = getSupportActionBar();

        WebViewClient client = new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                actionBar.setTitle(getString(R.string.loading));
                actionBar.setSubtitle(getHostName(url));
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                actionBar.setTitle(view.getTitle());
                progressBar.setVisibility(View.GONE);
            }
        };

        webView.setWebViewClient(client);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress + 10);
            }
        });
    }

    private void loadWebFromURL() {
        if (url == null)
            url = getIntent().getStringExtra(EXTRA_URL);

        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        webView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        webView.onPause();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_webview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            loadWebFromURL();
        } else if (item.getItemId() == R.id.action_browser) {
            redirectLinkToBrowser();
        } else
            onBackPressed();

        return true;
    }

    private void redirectLinkToBrowser() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(WebViewActivity.this, "Ops, Cannot open url", Toast.LENGTH_LONG).show();
        }
    }

    private String getHostName(String url) {
        try {
            URI uri = new URI(url);
            String new_url = uri.getHost();

            if (!new_url.startsWith("www.")) {
                new_url = "www." + new_url;
            }

            return new_url;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return url;
        }
    }

    public static void navigate(@NotNull Activity activity, @NotNull String url) {
        Intent navigationIntent = new Intent(activity, WebViewActivity.class)
                .putExtra(EXTRA_URL, url);

        activity.startActivity(navigationIntent);
    }
}