/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.projectseven;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);

    }

    TextView mTitle;
    TextView mDescription;
    TextView mAuthors;


    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.items, parent, false);
        }

        Book currentBook = getItem(position);

        mTitle = listItemView.findViewById(R.id.book_title);
        mAuthors = listItemView.findViewById(R.id.book_authors);
        mDescription = listItemView.findViewById(R.id.book_description);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.title = mTitle;
        viewHolder.author = mAuthors;
        viewHolder.description = mDescription;
        viewHolder.title.setText(currentBook.getBookTitle());
        viewHolder.author.setText(currentBook.getAllAuthors());
        viewHolder.description.setText(currentBook.getBookDescription());
        return listItemView;
    }

    static class ViewHolder {
        TextView title;
        TextView author;
        TextView description;
    }
}