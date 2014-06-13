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
 * �A�ŋ֎~�@�\�t��AjaxLink<br>
 * 
 * <em>�����F����i�ł�</em><br>
 * �����X�V���s�����߂�AjaxLink�̏ꍇ�A<br>
 * �Y�������̍X�V���������N������ł��Ă��܂����߁A<br>
 * �����N���N���b�N����ƈ�莞�ԑ���ł��Ȃ����̂��쐬�B<br>
 * �����N���N���b�N����ƁA�ȉ��̎菇�ŏ��������s����܂��B<br>
 * <ol type="1">
 * <li>�����N���N���b�N</li>
 * <li>�����N��Disable������</li>
 * <li>�����N�e�L�X�g����\����ԂɂȂ�</li>
 * <li>indicator���\����ԂɂȂ�</li>
 * <li>�����N�N���b�N���̏��������s�����</li>
 * <li>�^�C�}�[���N������</li>
 * <li>�ݒ肳�ꂽ���Ԃ��o�߂���</li>
 * <li>indicator����\����ԂɂȂ�</li>
 * <li>�����N�e�L�X�g���\����ԂɂȂ�</li>
 * <li>�����N��Enable������</li>
 * </ol>
 * @version 1.0.0
 * @since 2014/06/12
 * @author IM yui_inoue
 */
public class AjaxCanNotRollLinkPanel extends Panel {

    /**
     * �����N
     */
    private AjaxLink update;
    /**
     * �����N���e�L�X�g
     */
    private Label updateLabel;
    /**
     * indicator
     */
    private Image indicator;
    /**
     * �X�V�Ώۂ�Component
     */
    private Component targetComponent;
    /**
     * �����N��񊈐��ɂ������ {@link Duration}
     */
    private Duration waitTime;

    /**
     * Construct
     *
     * @param id
     * {@link Panel#Panel(java.lang.String, org.apache.wicket.model.IModel)}��id
     * @param label �����N�ɕ\�����镶����
     * @param targetComponent �X�V�Ώۂ�Component
     * @param waitTime �����N��񊈐��ɂ������ {@link Duration}
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
     * {@link Panel#Panel(java.lang.String, org.apache.wicket.model.IModel)}��id
     * @param model
     * {@link Panel#Panel(java.lang.String, org.apache.wicket.model.IModel)}��model
     * @param label �����N�ɕ\�����镶����
     * @param targetComponent �X�V�Ώۂ�Component
     * @param waitTime �����N��񊈐��ɂ������ {@link Duration}
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
     * Panel�쐬
     *
     * @param label �����N�ɕ\�����镶����
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
     * �����N�N���b�N���̃C�x���g<br>
     *
     * �����N�̎g�p�s����C���^�[�o�����J���Ă̕��A�̏����͊��Ɏ����ς݂̂���<br>
     * �����N���N���b�N�����Ƃ��ɔ�����������������Override�������super�ȉ��ɋL�ڂ��Ă�������<br>
     * �g�p��F<br>
     * <code>
     *  new AjaxCanNotRollLinkPanel(�`) {
     *      @Override
     *      protected void onClickEvent(AjaxRequestTarget target) {
     *          super.onClickEvent(target);
     *          �ȉ��N���b�N���̏������L�q
     *      }
     *  });
     * </code>
     * @param target Override�����ۂ͎g�p�֎~
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
