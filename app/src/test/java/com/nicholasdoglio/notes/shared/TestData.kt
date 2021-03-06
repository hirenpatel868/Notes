/*
 * MIT License
 *
 * Copyright (c) 2020 Nicholas Doglio
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

package com.nicholasdoglio.notes.shared

import com.google.common.truth.Truth
import com.nicholasdoglio.notes.Note
import org.threeten.bp.LocalDateTime

// TODO should this be an object?
object TestData {

    val firstNote: Note = Note.Impl(1, "First note", "This is my first note", LocalDateTime.now())
    val secondNote: Note =
        Note.Impl(2, "Second note", "This is my second note", LocalDateTime.now())
    val thirdNote: Note = Note.Impl(3, "Third note", "This is my third note", LocalDateTime.now())

    val someNotes: List<Note> = listOf(
        firstNote,
        secondNote,
        thirdNote
    )
}

fun Note.compareNote(otherNote: Note) {
    otherNote.apply {
        Truth.assertThat(this@compareNote.id).isEqualTo(otherNote.id)
        Truth.assertThat(this@compareNote.title).isEqualTo(otherNote.title)
        Truth.assertThat(this@compareNote.contents).isEqualTo(otherNote.contents)
    }
}
