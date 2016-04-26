/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package com.timvisee.voxeltex.architecture.component.mesh.renderer;

import com.timvisee.voxeltex.architecture.component.BaseComponent;
import com.timvisee.voxeltex.module.material.Material;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMeshRendererComponent extends BaseComponent implements MeshRendererComponentInterface {

    @Override
    public void create() { }

    @Override
    public void start() {
        // Call the super
        super.start();
    }

    @Override
    public synchronized void update() { }

    @Override
    public boolean hasMeshFilterComponent() {
        return getMeshFilterComponent() != null;
    }

    @Override
    public Material getMaterial() {
        return getMaterials().get(0);
    }

    @Override
    public Material getMaterial(int i) {
        return getMaterials().get(i);
    }

    @Override
    public int getMaterialCount() {
        return getMaterials().size();
    }

    @Override
    public boolean hasMaterial() {
        return getMaterialCount() > 0;
    }

    @Override
    public void setMaterial(Material material) {
        // Create a new list with the material
        List<Material> list = new ArrayList<>();
        list.add(material);

        // Set the materials
        setMaterials(list);
    }
}
