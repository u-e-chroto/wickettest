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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;

/**
 * 連打禁止機能付きAjaxLink<br>
 * 
 * <em>※注：試作品です</em><br>
 * 部分更新を行うためのAjaxLinkの場合、<br>
 * 該当部分の更新中もリンクが操作できてしまうため、<br>
 * リンクをクリックすると一定時間操作できないものを作成。<br>
 * リンクをクリックすると、以下の手順で処理が実行されます。<br>
 * <ol type="1">
 * <li>リンクをクリック</li>
 * <li>リンクがDisable化する</li>
 * <li>リンクテキストが非表示状態になる</li>
 * <li>indicatorが表示状態になる</li>
 * <li>リンククリック時の処理が実行される</li>
 * <li>タイマーが起動する</li>
 * <li>設定された時間が経過する</li>
 * <li>indicatorが非表示状態になる</li>
 * <li>リンクテキストが表示状態になる</li>
 * <li>リンクがEnable化する</li>
 * </ol>
 * @version 1.0.0
 * @since 2014/06/12
 * @author IM yui_inoue
 */
public class AjaxCanNotRollLinkPanel extends Panel {

    /**
     * リンク
     */
    private AjaxLink update;
    /**
     * リンク内テキスト
     */
    private Label updateLabel;
    /**
     * indicator
     */
    private Image indicator;
    /**
     * 更新対象のComponent
     */
    private Component targetComponent;
    /**
     * リンクを非活性にする期間 {@link Duration}
     */
    private Duration waitTime;

    /**
     * Construct
     *
     * @param id
     * {@link Panel#Panel(java.lang.String, org.apache.wicket.model.IModel)}のid
     * @param label リンクに表示する文字列
     * @param targetComponent 更新対象のComponent
     * @param waitTime リンクを非活性にする期間 {@link Duration}
     * @see Panel
     */
    public AjaxCanNotRollLinkPanel(String id, String label,
            Component targetComponent, Duration waitTime) {
        super(id);
        this.targetComponent = targetComponent;
        this.waitTime = waitTime;
        init(label);
    }

    /**
     * Construct
     *
     * @param id
     * {@link Panel#Panel(java.lang.String, org.apache.wicket.model.IModel)}のid
     * @param model
     * {@link Panel#Panel(java.lang.String, org.apache.wicket.model.IModel)}のmodel
     * @param label リンクに表示する文字列
     * @param targetComponent 更新対象のComponent
     * @param waitTime リンクを非活性にする期間 {@link Duration}
     * @see Panel
     */
    public AjaxCanNotRollLinkPanel(String id, IModel<?> model, String label,
            Component targetComponent, Duration waitTime) {
        super(id, model);
        this.targetComponent = targetComponent;
        this.waitTime = waitTime;
        init(label);
    }

    /**
     * Panel作成
     *
     * @param label リンクに表示する文字列
     */
    private void init(String label) {
        updateLabel = new Label("updateLabel", label);
        updateLabel.setOutputMarkupId(true);

        indicator = new Image("indicator", AbstractDefaultAjaxBehavior.INDICATOR);
        indicator.setOutputMarkupId(true);
        indicator.setVisible(false);

        update = new AjaxLink("update") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                onClickEvent(target);
            }
        };
        update.setOutputMarkupId(true);
        update.add(updateLabel);
        update.add(indicator);
        add(update);
    }

    /**
     * リンククリック時のイベント<br>
     *
     * リンクの使用不可からインターバルを開けての復帰の処理は既に実装済みのため<br>
     * リンクをクリックしたときに発生させたい処理をOverrideした上でsuper以下に記載してください<br>
     * 使用例：<br>
     * <code>
     *  new AjaxCanNotRollLinkPanel(～) {
     *      @Override
     *      protected void onClickEvent(AjaxRequestTarget target) {
     *          super.onClickEvent(target);
     *          以下クリック時の処理を記述
     *      }
     *  });
     * </code>
     * @param target Overrideした際は使用禁止
     */
    protected void onClickEvent(AjaxRequestTarget target) {
        update.setEnabled(false);
        updateLabel.setVisible(false);
        indicator.setVisible(true);
        this.add(new AbstractAjaxTimerBehavior(waitTime) {
            @Override
            protected void onTimer(AjaxRequestTarget target) {
                update.setEnabled(true);
                updateLabel.setVisible(true);
                indicator.setVisible(false);
                this.stop();
                target.add(this.getComponent());
            }
        });
        target.add(targetComponent);
    }
}
