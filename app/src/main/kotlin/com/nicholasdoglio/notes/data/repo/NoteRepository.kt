/*
 * MIT License
 *
 * Copyright (c) 2019 Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nicholasdoglio.notes.data.repo

import com.nicholasdoglio.notes.data.local.NoteDao
import com.nicholasdoglio.notes.data.model.Note
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class NoteRepository @Inject constructor(private val dao: NoteDao) : Repository<Note> {

    override val observeCountOfItems: Flow<Int> = dao.observeNumOfNotes

    override val observeItems: Flow<List<Note>> = dao.observeNotes

    override fun findItemById(id: Long): Flow<Note?> = dao.findNoteById(id)

    override suspend fun upsert(item: Note) {
        val success = dao.insertNote(item)

        if (success == FAILED_INSERT) {
            dao.updateNote(item)
        }
    }

    override suspend fun delete(item: Note) {
        dao.deleteNote(item)
    }

    private companion object {
        private const val FAILED_INSERT = -1L
    }
}
