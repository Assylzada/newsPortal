package com.example.newsportal;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.component.page.Push;

@Push
@PWA(name = "News Portal", shortName = "News")
public class AppShell implements AppShellConfigurator {
}
