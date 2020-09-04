/*
 * Copyright 2020 Adobe. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
 * OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.adobe.aem.spa.project.core.internal.impl;

import java.util.Collections;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.day.cq.wcm.api.components.AnalyzeContext;
import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.components.ComponentContext;
import com.day.cq.wcm.api.components.EditContext;
import com.day.cq.wcm.api.designer.Cell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class HierarchyComponentContextWrapperTest {

    @Mock
    private ComponentContext componentContext;

    private com.day.cq.wcm.api.Page page = spy(com.day.cq.wcm.api.Page.class);

    private HierarchyComponentContextWrapper hccw;

    private ComponentContext getMockedComponentContext() {
        when(componentContext.getParent()).thenReturn(mock(ComponentContext.class));
        when(componentContext.getRoot()).thenReturn(mock(ComponentContext.class));
        when(componentContext.isRoot()).thenReturn(Boolean.TRUE);
        when(componentContext.getResource()).thenReturn(mock(Resource.class));
        when(componentContext.getCell()).thenReturn(mock(Cell.class));
        when(componentContext.getEditContext()).thenReturn(mock(EditContext.class));
        when(componentContext.getAnalyzeContext()).thenReturn(mock(AnalyzeContext.class));
        when(componentContext.getComponent()).thenReturn(mock(Component.class));
        when(componentContext.getAttribute(anyString())).thenReturn(mock(Object.class));
        when(componentContext.getCssClassNames()).thenReturn(Collections.EMPTY_SET);
        when(componentContext.hasDecoration()).thenReturn(Boolean.TRUE);
        when(componentContext.getDecorationTagName()).thenReturn("decorationTagName");
        when(componentContext.getDefaultDecorationTagName()).thenReturn("defaultDecorationTagName");

        return componentContext;
    }

    @BeforeEach
    void beforeEach() {
        componentContext = getMockedComponentContext();
        hccw = new HierarchyComponentContextWrapper(componentContext, page);
    }

    @Test
    void testGetters() {
        assertEquals(componentContext.getParent(), hccw.getParent());
        assertEquals(componentContext.getRoot(), hccw.getRoot());
        assertEquals(componentContext.isRoot(), hccw.isRoot());
        assertEquals(componentContext.getResource(), hccw.getResource());
        assertEquals(componentContext.getCell(), hccw.getCell());
        assertEquals(componentContext.getEditContext(), hccw.getEditContext());
        assertEquals(componentContext.getAnalyzeContext(), hccw.getAnalyzeContext());
        assertEquals(componentContext.getComponent(), hccw.getComponent());
        assertEquals(page, hccw.getPage());
        assertEquals(componentContext.getAttribute("1"), hccw.getAttribute("1"));
        assertEquals(componentContext.getCssClassNames(), hccw.getCssClassNames());
        assertEquals(componentContext.hasDecoration(), hccw.hasDecoration());
        assertEquals(componentContext.getDecorationTagName(), hccw.getDecorationTagName());
        assertEquals(componentContext.getDefaultDecorationTagName(), hccw.getDefaultDecorationTagName());
    }

    @Test
    void testSetDecorationTagName() {
        verify(componentContext, times(0)).setDecorationTagName(anyString());
        hccw.setDecorationTagName("decorationTagName");
        verify(componentContext, times(1)).setDecorationTagName(anyString());
    }

    @Test
    void testSetAttribute() {
        verify(componentContext, times(0)).setAttribute(anyString(), anyObject());
        hccw.setAttribute("attributeName", "attributeValue");
        verify(componentContext, times(1)).setAttribute(anyString(), anyObject());
    }

    @Test
    void testSetDecorate() {
        verify(componentContext, times(0)).setDecorate(anyBoolean());
        hccw.setDecorate(Boolean.TRUE);
        verify(componentContext, times(1)).setDecorate(anyBoolean());
    }

    @Test
    void testSetDefaultDecorationTagName() {
        verify(componentContext, times(0)).setDecorationTagName(anyString());
        hccw.setDefaultDecorationTagName("defaultDecorationTagName");
        verify(componentContext, times(1)).setDecorationTagName(anyString());
    }
}
