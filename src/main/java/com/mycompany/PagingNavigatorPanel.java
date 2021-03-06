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
                new ListElement("1", "1/1", "金", "元日"),
                new ListElement("2", "1/11", "月", "成人の日"),
                new ListElement("3", "2/11", "木", "建国記念の日"),
                new ListElement("4", "3/21", "日", "春分の日"),
                new ListElement("5", "3/22", "月", "振替休日"),
                new ListElement("6", "4/29", "木", "昭和の日"),
                new ListElement("7", "5/3", "月", "憲法記念日"),
                new ListElement("8", "5/4", "火", "みどりの日"),
                new ListElement("9", "5/5", "水", "こどもの日"),
                new ListElement("10", "7/19", "月", "海の日"),
                new ListElement("11", "9/20", "月", "敬老の日"),
                new ListElement("12", "9/23", "木", "秋分の日"),
                new ListElement("13", "10/11", "月", "体育の日"),
                new ListElement("14", "11/3", "水", "文化の日"),
                new ListElement("15", "11/23", "火", "勤労感謝の日"),
                new ListElement("16", "12/23", "木", "天皇誕生日"));
        returnMap.put("2010", data2010);
        
        List<ListElement> data2011 = Arrays.asList(
                new ListElement("1", "1/1", "土", "元日"),
                new ListElement("2", "1/10", "月", "成人の日"),
                new ListElement("3", "2/11", "金", "建国記念の日"),
                new ListElement("4", "3/21", "月", "春分の日"),
                new ListElement("5", "4/29", "金", "昭和の日"),
                new ListElement("6", "5/3", "火", "憲法記念日"),
                new ListElement("7", "5/4", "水", "みどりの日"),
                new ListElement("8", "5/5", "木", "こどもの日"),
                new ListElement("9", "7/18", "月", "海の日"),
                new ListElement("10", "9/19", "月", "敬老の日"),
                new ListElement("11", "9/23", "金", "秋分の日"),
                new ListElement("12", "10/10", "月", "体育の日"),
                new ListElement("13", "11/3", "木", "文化の日"),
                new ListElement("14", "11/23", "水", "勤労感謝の日"),
                new ListElement("15", "12/23", "金", "天皇誕生日"));
        returnMap.put("2011", data2011);
        
        List<ListElement> data2012 = Arrays.asList(
                new ListElement("1", "1/1", "日", "元日"),
                new ListElement("2", "1/2", "月", "振替休日"),
                new ListElement("3", "1/9", "月", "成人の日"),
                new ListElement("4", "2/11", "土", "建国記念の日"),
                new ListElement("5", "3/20", "火", "春分の日"),
                new ListElement("6", "4/29", "日", "昭和の日"),
                new ListElement("7", "4/30", "月", "振替休日"),
                new ListElement("8", "5/3", "木", "憲法記念日"),
                new ListElement("9", "5/4", "金", "みどりの日"),
                new ListElement("10", "5/5", "土", "こどもの日"),
                new ListElement("11", "7/16", "月", "海の日"),
                new ListElement("12", "9/17", "月", "敬老の日"),
                new ListElement("13", "9/22", "土", "秋分の日"),
                new ListElement("14", "10/8", "月", "体育の日"),
                new ListElement("15", "11/3", "土", "文化の日"),
                new ListElement("16", "11/23", "金", "勤労感謝の日"),
                new ListElement("17", "12/23", "日", "天皇誕生日"),
                new ListElement("18", "12/24", "月", "振替休日"));
        returnMap.put("2012", data2012);
        
        List<ListElement> data2013 = Arrays.asList(
                new ListElement("1", "1/1", "火", "元日"),
                new ListElement("2", "1/14", "月", "成人の日"),
                new ListElement("3", "2/11", "月", "建国記念の日"),
                new ListElement("4", "3/20", "水", "春分の日"),
                new ListElement("5", "4/29", "月", "昭和の日"),
                new ListElement("6", "5/3", "金", "憲法記念日"),
                new ListElement("7", "5/4", "土", "みどりの日"),
                new ListElement("8", "5/5", "日", "こどもの日"),
                new ListElement("9", "5/6", "月", "振替休日"),
                new ListElement("10", "7/15", "月", "海の日"),
                new ListElement("11", "9/16", "月", "敬老の日"),
                new ListElement("12", "9/23", "月", "秋分の日"),
                new ListElement("13", "10/14", "月", "体育の日"),
                new ListElement("14", "11/3", "日", "文化の日"),
                new ListElement("15", "11/4", "月", "振替休日"),
                new ListElement("16", "11/23", "土", "勤労感謝の日"),
                new ListElement("17", "12/23", "月", "天皇誕生日"));
        returnMap.put("2013", data2013);
        
        List<ListElement> data2014 = Arrays.asList(
                new ListElement("1", "1/1", "水", "元日"),
                new ListElement("2", "1/13", "月", "成人の日"),
                new ListElement("3", "2/11", "火", "建国記念の日"),
                new ListElement("4", "3/21", "金", "春分の日"),
                new ListElement("5", "4/29", "火", "昭和の日"),
                new ListElement("6", "5/3", "土", "憲法記念日"),
                new ListElement("7", "5/4", "日", "みどりの日"),
                new ListElement("8", "5/5", "月", "こどもの日"),
                new ListElement("9", "5/6", "火", "振替休日"),
                new ListElement("10", "7/21", "月", "海の日"),
                new ListElement("11", "9/15", "月", "敬老の日"),
                new ListElement("12", "9/23", "火", "秋分の日"),
                new ListElement("13", "10/13", "月", "体育の日"),
                new ListElement("14", "11/3", "月", "文化の日"),
                new ListElement("15", "11/23", "日", "勤労感謝の日"),
                new ListElement("16", "11/24", "月", "振替休日"),
                new ListElement("17", "12/23", "火", "天皇誕生日"));
        returnMap.put("2014", data2014);
        
        List<ListElement> data2015 = Arrays.asList(
                new ListElement("1", "1/1", "木", "元日"),
                new ListElement("2", "1/12", "月", "成人の日"),
                new ListElement("3", "2/11", "水", "建国記念の日"),
                new ListElement("4", "3/21", "土", "春分の日"),
                new ListElement("5", "4/29", "水", "昭和の日"),
                new ListElement("6", "5/3", "日", "憲法記念日"),
                new ListElement("7", "5/4", "月", "みどりの日"),
                new ListElement("8", "5/5", "火", "こどもの日"),
                new ListElement("9", "5/6", "水", "振替休日"),
                new ListElement("10", "7/20", "月", "海の日"),
                new ListElement("11", "9/21", "月", "敬老の日"),
                new ListElement("12", "9/22", "火", "国民の休日"),
                new ListElement("13", "9/23", "水", "秋分の日"),
                new ListElement("14", "10/12", "月", "体育の日"),
                new ListElement("15", "11/3", "火", "文化の日"),
                new ListElement("16", "11/23", "月", "勤労感謝の日"),
                new ListElement("17", "12/23", "水", "天皇誕生日"));
        returnMap.put("2015", data2015);
        
        return returnMap;
    }
}
