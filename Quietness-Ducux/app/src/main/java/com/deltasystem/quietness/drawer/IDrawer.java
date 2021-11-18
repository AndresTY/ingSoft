package com.deltasystem.quietness.drawer;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;

public interface IDrawer {

    public void ClickMenu(View view);

    public void openDrawer(DrawerLayout drawerLayout);

    public void ClickLogo(View view);

    public void closeDrawer(DrawerLayout drawerLayout);

    public void ClickHome(View view);

    public void ClickSueno(View view);

    public void ClickMusica(View view);

    public void ClickCalendario(View view);

    public void ClickTips(View view);

    public void ClickSettings(View view);

    public void ClickError(View view);

    public void ClickLogout(View view);

    public void redireccionar(Activity activity, Class aClass);
}