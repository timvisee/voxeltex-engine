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

package com.timvisee.voxeltex.component.mesh.filter;

import com.timvisee.voxeltex.mesh.Mesh;

public interface MeshFilterComponentInterface {

    /**
     * Get the mesh that is attached to this mesh filter.
     *
     * @return Mesh, or null if no mesh is attached.
     */
    Mesh getMesh();

    /**
     * Attach a new mesh to this mesh filter.
     *
     * @param mesh Mesh to attach.
     */
    void setMesh(Mesh mesh);

    /**
     * Check whether this mesh filter has a mesh attached.
     *
     * @return True if attached, false if not.
     */
    boolean hasMesh();
}
