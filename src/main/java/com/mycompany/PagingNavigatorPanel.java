/*
 * Copyright 2014 yui_inoue.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author yui_inoue
 */
public class PagingNavigatorPanel extends Panel {
    
    public PagingNavigatorPanel(String id, String name, final String value) {
        super(id);
        Label output = new Label("output", name);
        output.setOutputMarkupId(true);
        add(output);
        
        HashMap<String, List<ListElement>> dataMap = createDataMap();
        
        final DataView<ListElement> view = new DataView<ListElement>("holidayData",
                new ListDataProvider<ListElement>(dataMap.get(value)), 5) {
            @Override
            protected void populateItem(Item<ListElement> item) {
                ListElement element = item.getModelObject();
                item.add(new Label("no", new PropertyModel<String>(element, "no")));
                item.add(new Label("monthDay", new PropertyModel<String>(element, "monthDay")));
                item.add(new Label("week", new PropertyModel<String>(element, "week")));
                item.add(new Label("name", new PropertyModel<String>(element, "name")));
            }
        };
        view.setOutputMarkupId(true);
        
        final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
        dataContainer.setOutputMarkupId(true);
        dataContainer.add(new AjaxLazyLoadPanel("dataView") {
            @Override
            public Component getLazyLoadComponent(String markupId) {
                return new DataListPanel(markupId, value, view);
            }
        });
        
        add(dataContainer);
        add(new AjaxPagingNavigator("paging", view));
    }
    
    private HashMap<String, List<ListElement>> createDataMap() {
        HashMap returnMap = new HashMap();
        List<ListElement> data2010 = Arrays.asList(
                new ListElement("1", "1/1", "‹à", "Œ³“ú"),
                new ListElement("2", "1/11", "Œ", "¬l‚Ì“ú"),
                new ListElement("3", "2/11", "–Ø", "Œš‘‹L”O‚Ì“ú"),
                new ListElement("4", "3/21", "“ú", "t•ª‚Ì“ú"),
                new ListElement("5", "3/22", "Œ", "U‘Ö‹x“ú"),
                new ListElement("6", "4/29", "–Ø", "º˜a‚Ì“ú"),
                new ListElement("7", "5/3", "Œ", "Œ›–@‹L”O“ú"),
                new ListElement("8", "5/4", "‰Î", "‚İ‚Ç‚è‚Ì“ú"),
                new ListElement("9", "5/5", "…", "‚±‚Ç‚à‚Ì“ú"),
                new ListElement("10", "7/19", "Œ", "ŠC‚Ì“ú"),
                new ListElement("11", "9/20", "Œ", "Œh˜V‚Ì“ú"),
                new ListElement("12", "9/23", "–Ø", "H•ª‚Ì“ú"),
                new ListElement("13", "10/11", "Œ", "‘Ìˆç‚Ì“ú"),
                new ListElement("14", "11/3", "…", "•¶‰»‚Ì“ú"),
                new ListElement("15", "11/23", "‰Î", "‹Î˜JŠ´Ó‚Ì“ú"),
                new ListElement("16", "12/23", "–Ø", "“Vc’a¶“ú"));
        returnMap.put("2010", data2010);
        
        List<ListElement> data2011 = Arrays.asList(
                new ListElement("1", "1/1", "“y", "Œ³“ú"),
                new ListElement("2", "1/10", "Œ", "¬l‚Ì“ú"),
                new ListElement("3", "2/11", "‹à", "Œš‘‹L”O‚Ì“ú"),
                new ListElement("4", "3/21", "Œ", "t•ª‚Ì“ú"),
                new ListElement("5", "4/29", "‹à", "º˜a‚Ì“ú"),
                new ListElement("6", "5/3", "‰Î", "Œ›–@‹L”O“ú"),
                new ListElement("7", "5/4", "…", "‚İ‚Ç‚è‚Ì“ú"),
                new ListElement("8", "5/5", "–Ø", "‚±‚Ç‚à‚Ì“ú"),
                new ListElement("9", "7/18", "Œ", "ŠC‚Ì“ú"),
                new ListElement("10", "9/19", "Œ", "Œh˜V‚Ì“ú"),
                new ListElement("11", "9/23", "‹à", "H•ª‚Ì“ú"),
                new ListElement("12", "10/10", "Œ", "‘Ìˆç‚Ì“ú"),
                new ListElement("13", "11/3", "–Ø", "•¶‰»‚Ì“ú"),
                new ListElement("14", "11/23", "…", "‹Î˜JŠ´Ó‚Ì“ú"),
                new ListElement("15", "12/23", "‹à", "“Vc’a¶“ú"));
        returnMap.put("2011", data2011);
        
        List<ListElement> data2012 = Arrays.asList(
                new ListElement("1", "1/1", "“ú", "Œ³“ú"),
                new ListElement("2", "1/2", "Œ", "U‘Ö‹x“ú"),
                new ListElement("3", "1/9", "Œ", "¬l‚Ì“ú"),
                new ListElement("4", "2/11", "“y", "Œš‘‹L”O‚Ì“ú"),
                new ListElement("5", "3/20", "‰Î", "t•ª‚Ì“ú"),
                new ListElement("6", "4/29", "“ú", "º˜a‚Ì“ú"),
                new ListElement("7", "4/30", "Œ", "U‘Ö‹x“ú"),
                new ListElement("8", "5/3", "–Ø", "Œ›–@‹L”O“ú"),
                new ListElement("9", "5/4", "‹à", "‚İ‚Ç‚è‚Ì“ú"),
                new ListElement("10", "5/5", "“y", "‚±‚Ç‚à‚Ì“ú"),
                new ListElement("11", "7/16", "Œ", "ŠC‚Ì“ú"),
                new ListElement("12", "9/17", "Œ", "Œh˜V‚Ì“ú"),
                new ListElement("13", "9/22", "“y", "H•ª‚Ì“ú"),
                new ListElement("14", "10/8", "Œ", "‘Ìˆç‚Ì“ú"),
                new ListElement("15", "11/3", "“y", "•¶‰»‚Ì“ú"),
                new ListElement("16", "11/23", "‹à", "‹Î˜JŠ´Ó‚Ì“ú"),
                new ListElement("17", "12/23", "“ú", "“Vc’a¶“ú"),
                new ListElement("18", "12/24", "Œ", "U‘Ö‹x“ú"));
        returnMap.put("2012", data2012);
        
        List<ListElement> data2013 = Arrays.asList(
                new ListElement("1", "1/1", "‰Î", "Œ³“ú"),
                new ListElement("2", "1/14", "Œ", "¬l‚Ì“ú"),
                new ListElement("3", "2/11", "Œ", "Œš‘‹L”O‚Ì“ú"),
                new ListElement("4", "3/20", "…", "t•ª‚Ì“ú"),
                new ListElement("5", "4/29", "Œ", "º˜a‚Ì“ú"),
                new ListElement("6", "5/3", "‹à", "Œ›–@‹L”O“ú"),
                new ListElement("7", "5/4", "“y", "‚İ‚Ç‚è‚Ì“ú"),
                new ListElement("8", "5/5", "“ú", "‚±‚Ç‚à‚Ì“ú"),
                new ListElement("9", "5/6", "Œ", "U‘Ö‹x“ú"),
                new ListElement("10", "7/15", "Œ", "ŠC‚Ì“ú"),
                new ListElement("11", "9/16", "Œ", "Œh˜V‚Ì“ú"),
                new ListElement("12", "9/23", "Œ", "H•ª‚Ì“ú"),
                new ListElement("13", "10/14", "Œ", "‘Ìˆç‚Ì“ú"),
                new ListElement("14", "11/3", "“ú", "•¶‰»‚Ì“ú"),
                new ListElement("15", "11/4", "Œ", "U‘Ö‹x“ú"),
                new ListElement("16", "11/23", "“y", "‹Î˜JŠ´Ó‚Ì“ú"),
                new ListElement("17", "12/23", "Œ", "“Vc’a¶“ú"));
        returnMap.put("2013", data2013);
        
        List<ListElement> data2014 = Arrays.asList(
                new ListElement("1", "1/1", "…", "Œ³“ú"),
                new ListElement("2", "1/13", "Œ", "¬l‚Ì“ú"),
                new ListElement("3", "2/11", "‰Î", "Œš‘‹L”O‚Ì“ú"),
                new ListElement("4", "3/21", "‹à", "t•ª‚Ì“ú"),
                new ListElement("5", "4/29", "‰Î", "º˜a‚Ì“ú"),
                new ListElement("6", "5/3", "“y", "Œ›–@‹L”O“ú"),
                new ListElement("7", "5/4", "“ú", "‚İ‚Ç‚è‚Ì“ú"),
                new ListElement("8", "5/5", "Œ", "‚±‚Ç‚à‚Ì“ú"),
                new ListElement("9", "5/6", "‰Î", "U‘Ö‹x“ú"),
                new ListElement("10", "7/21", "Œ", "ŠC‚Ì“ú"),
                new ListElement("11", "9/15", "Œ", "Œh˜V‚Ì“ú"),
                new ListElement("12", "9/23", "‰Î", "H•ª‚Ì“ú"),
                new ListElement("13", "10/13", "Œ", "‘Ìˆç‚Ì“ú"),
                new ListElement("14", "11/3", "Œ", "•¶‰»‚Ì“ú"),
                new ListElement("15", "11/23", "“ú", "‹Î˜JŠ´Ó‚Ì“ú"),
                new ListElement("16", "11/24", "Œ", "U‘Ö‹x“ú"),
                new ListElement("17", "12/23", "‰Î", "“Vc’a¶“ú"));
        returnMap.put("2014", data2014);
        
        List<ListElement> data2015 = Arrays.asList(
                new ListElement("1", "1/1", "–Ø", "Œ³“ú"),
                new ListElement("2", "1/12", "Œ", "¬l‚Ì“ú"),
                new ListElement("3", "2/11", "…", "Œš‘‹L”O‚Ì“ú"),
                new ListElement("4", "3/21", "“y", "t•ª‚Ì“ú"),
                new ListElement("5", "4/29", "…", "º˜a‚Ì“ú"),
                new ListElement("6", "5/3", "“ú", "Œ›–@‹L”O“ú"),
                new ListElement("7", "5/4", "Œ", "‚İ‚Ç‚è‚Ì“ú"),
                new ListElement("8", "5/5", "‰Î", "‚±‚Ç‚à‚Ì“ú"),
                new ListElement("9", "5/6", "…", "U‘Ö‹x“ú"),
                new ListElement("10", "7/20", "Œ", "ŠC‚Ì“ú"),
                new ListElement("11", "9/21", "Œ", "Œh˜V‚Ì“ú"),
                new ListElement("12", "9/22", "‰Î", "‘–¯‚Ì‹x“ú"),
                new ListElement("13", "9/23", "…", "H•ª‚Ì“ú"),
                new ListElement("14", "10/12", "Œ", "‘Ìˆç‚Ì“ú"),
                new ListElement("15", "11/3", "‰Î", "•¶‰»‚Ì“ú"),
                new ListElement("16", "11/23", "Œ", "‹Î˜JŠ´Ó‚Ì“ú"),
                new ListElement("17", "12/23", "…", "“Vc’a¶“ú"));
        returnMap.put("2015", data2015);
        
        return returnMap;
    }
}
