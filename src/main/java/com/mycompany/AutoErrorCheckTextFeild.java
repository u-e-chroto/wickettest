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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

/**
 *
 * @author yui_inoue
 */
public abstract class AutoErrorCheckTextFeild<T> extends TextField<T> {

    public AutoErrorCheckTextFeild(String id) {
        super(id);
        init();
    }

    public AutoErrorCheckTextFeild(String id, Class<T> type) {
        super(id, type);
        init();
    }

    public AutoErrorCheckTextFeild(String id, IModel<T> model) {
        super(id, model);
        init();
    }

    public AutoErrorCheckTextFeild(String id, IModel<T> model, Class<T> type) {
        super(id, model, type);
        init();
    }
    
    private void init() {
        this.add(new AjaxFormComponentUpdatingBehavior("onblur") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                errorCheck(target);
                target.add(this.getComponent());
            }
        });
        this.setOutputMarkupId(true);
    }
    
    protected abstract void errorCheck(AjaxRequestTarget target);
}
