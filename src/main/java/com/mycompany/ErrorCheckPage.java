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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

/**
 *
 * @author yui_inoue
 */
public class ErrorCheckPage extends WebPage {

    AutoErrorCheckTextFeild<String> inputText;

    public ErrorCheckPage() {
        final WebMarkupContainer updateComponent = new WebMarkupContainer("updateComponent");
        updateComponent.setOutputMarkupId(true);
        add(updateComponent);

        inputText = new AutoErrorCheckTextFeild<String>("inputText", new Model<String>("")) {
            @Override
            protected void errorCheck(AjaxRequestTarget target) {
                String message = "";
                String input = getModelObject();
                if (input == null || "".equals(input)) {
                    message = "入力されてないじゃないですかやだー！！";
                }

                if ("".equals(message)) {
                    String regex = "\\A[-]?[0-9]+\\z";
                    Pattern p = Pattern.compile(regex);
                    Matcher m1 = p.matcher(input);
                    if (!m1.find()) {
                        message = "数字じゃないじゃないですかばかー！！";
                    }
                }
                if ("".equals(message)) {
                    if (input.length() < 2 || input.length() > 10) {
                        message = "2桁以上10桁以下って、いいましたよね…？";
                    }
                }
                if (!"".equals(message)) {
                    setModel(new Model(""));
                    StringBuilder js = new StringBuilder("alert('");
                    js.append(message);
                    js.append("');");
                    target.appendJavaScript(new String(js));
                    target.focusComponent(this);
                }
            }
        };
        updateComponent.add(inputText);
    }
}
