/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Header:$
 */
package org.apache.beehive.samples.petstore.controls;

import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.samples.petstore.model.Item;
import org.apache.beehive.samples.petstore.model.Product;
import org.apache.beehive.samples.petstore.model.Category;
import org.apache.beehive.samples.petstore.controls.exceptions.InvalidIdentifierException;

@ControlInterface
    public interface CatalogControl {

    public Category[] getCategoryList();

    public Category getCategory(String categoryId) throws InvalidIdentifierException;

    public Product getProduct(String productId) throws InvalidIdentifierException;

    public Product[] getProductListByCategory(String categoryId);

    public Product[] searchProductList(String searchString);

    public Item getItem(String itemId) throws InvalidIdentifierException;

    public Item[] getItemListByProduct(String categoryId);

    public void updateItemQuantity(Item item, int quantity)
        throws InvalidIdentifierException;
	
	public void initDB(String initString);
}