package com.nicholasdoglio.notes.ui.common

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.nicholasdoglio.notes.R
import com.nicholasdoglio.notes.ui.MainActivity
import com.nicholasdoglio.notes.ui.about.AboutFragment
import com.nicholasdoglio.notes.ui.about.libs.LibsFragment
import com.nicholasdoglio.notes.ui.list.NoteListFragment
import com.nicholasdoglio.notes.ui.note.NoteFragment
import com.nicholasdoglio.notes.util.hideKeyboard
import com.nicholasdoglio.notes.util.showFragment
import javax.inject.Inject

class NavigationController
@Inject
constructor(private val mainActivity: MainActivity) { //How do I test this class?
    private val containerId: Int = R.id.fragmentContainer

    fun openFragment(savedInstanceState: Bundle?, intent: String = "") {
        if (savedInstanceState == null) {
            openList()
        }
        if ("createNote" == intent && savedInstanceState == null) {
            openNote()
        }
    }

    fun openList() {
        mainActivity.hideKeyboard()
        mainActivity.supportFragmentManager
            .beginTransaction()
            .replace(containerId, NoteListFragment(), "NOTE_LIST")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .setReorderingAllowed(true)
            .commit()
    }

    fun openNote(id: Long = 0) {
        mainActivity.showFragment(
            NoteFragment.create(id),
            "NOTE",
            "NOTE",
            FragmentTransaction.TRANSIT_FRAGMENT_OPEN,
            containerId
        )
    }

    fun openAbout() {
        mainActivity.showFragment(
            AboutFragment(),
            "ABOUT",
            "ABOUT",
            FragmentTransaction.TRANSIT_FRAGMENT_OPEN,
            containerId
        )
    }

    fun openLibs() {
        mainActivity.showFragment(
            LibsFragment(),
            "LIBS",
            "LIBS",
            FragmentTransaction.TRANSIT_FRAGMENT_OPEN,
            containerId
        )
    }
}