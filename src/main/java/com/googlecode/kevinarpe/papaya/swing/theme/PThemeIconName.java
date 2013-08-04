package com.googlecode.kevinarpe.papaya.swing.theme;

/*
 * #%L
 * This file is part of Papaya Swing.
 * %%
 * Copyright (C) 2013 Kevin Connor ARPE (kevinarpe@gmail.com)
 * %%
 * Papaya Swing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GPL Classpath Exception:
 * This project is subject to the "Classpath" exception as provided in
 * the LICENSE file that accompanied this code.
 * 
 * Papaya Swing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Papaya Swing.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import com.googlecode.kevinarpe.papaya.StringUtils;
import com.googlecode.kevinarpe.papaya.annotation.FullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.argument.StringArgs;

/**
 * Standard theme icon names as defined by <a href="www.freedesktop.org">www.freedesktop.org</a>.
 * <p>
 * Ref: <a href="http://standards.freedesktop.org/icon-naming-spec/icon-naming-spec-latest.html"
 * >http://standards.freedesktop.org/icon-naming-spec/icon-naming-spec-latest.html</a>
 * <p>
 * Attribution notice: Most of the comments for enum values below are copied from the referenced
 * spec (Version 0.8.90).  Copyright for this content remains with that of the original author:
 * Rodney Dawes (dobey.pwns@gmail.com)
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PThemeIconContextName
 */
@FullyTested
public enum PThemeIconName {

    /**
     * address-book-new: The icon used for the action to create a new address book.
     */
    ADDRESS_BOOK_NEW(PThemeIconContextName.ACTIONS, "address-book-new"),

    /**
     * application-exit: The icon used for exiting an application. Typically this is seen in the application's menus as File->Quit.
     */
    APPLICATION_EXIT(PThemeIconContextName.ACTIONS, "application-exit"),

    /**
     * appointment-new: The icon used for the action to create a new appointment in a calendaring application.
     */
    APPOINTMENT_NEW(PThemeIconContextName.ACTIONS, "appointment-new"),

    /**
     * call-start: The icon used for initiating or accepting a call. Should be similar to the standard cellular call pickup icon, a green handset with ear and mouth pieces facing upward.
     */
    CALL_START(PThemeIconContextName.ACTIONS, "call-start"),

    /**
     * call-stop: The icon used for stopping a current call. Should be similar to the standard cellular call hangup icon, a red handset with ear and mouth pieces facing downward.
     */
    CALL_STOP(PThemeIconContextName.ACTIONS, "call-stop"),

    /**
     * contact-new: The icon used for the action to create a new contact in an address book application.
     */
    CONTACT_NEW(PThemeIconContextName.ACTIONS, "contact-new"),

    /**
     * document-new: The icon used for the action to create a new document.
     */
    DOCUMENT_NEW(PThemeIconContextName.ACTIONS, "document-new"),

    /**
     * document-open: The icon used for the action to open a document.
     */
    DOCUMENT_OPEN(PThemeIconContextName.ACTIONS, "document-open"),

    /**
     * document-open-recent: The icon used for the action to open a document that was recently opened.
     */
    DOCUMENT_OPEN_RECENT(PThemeIconContextName.ACTIONS, "document-open-recent"),

    /**
     * document-page-setup: The icon for the page setup action of a document editor.
     */
    DOCUMENT_PAGE_SETUP(PThemeIconContextName.ACTIONS, "document-page-setup"),

    /**
     * document-print: The icon for the print action of an application.
     */
    DOCUMENT_PRINT(PThemeIconContextName.ACTIONS, "document-print"),

    /**
     * document-print-preview: The icon for the print preview action of an application.
     */
    DOCUMENT_PRINT_PREVIEW(PThemeIconContextName.ACTIONS, "document-print-preview"),

    /**
     * document-properties: The icon for the action to view the properties of a document in an application.
     */
    DOCUMENT_PROPERTIES(PThemeIconContextName.ACTIONS, "document-properties"),

    /**
     * document-revert: The icon for the action of reverting to a previous version of a document.
     */
    DOCUMENT_REVERT(PThemeIconContextName.ACTIONS, "document-revert"),

    /**
     * document-save: The icon for the save action. Should be an arrow pointing down and toward a hard disk.
     */
    DOCUMENT_SAVE(PThemeIconContextName.ACTIONS, "document-save"),

    /**
     * document-save-as: The icon for the save as action.
     */
    DOCUMENT_SAVE_AS(PThemeIconContextName.ACTIONS, "document-save-as"),

    /**
     * document-send: The icon for the send action. Should be an arrow pointing up and away from a hard disk.
     */
    DOCUMENT_SEND(PThemeIconContextName.ACTIONS, "document-send"),

    /**
     * edit-clear: The icon for the clear action.
     */
    EDIT_CLEAR(PThemeIconContextName.ACTIONS, "edit-clear"),

    /**
     * edit-copy: The icon for the copy action.
     */
    EDIT_COPY(PThemeIconContextName.ACTIONS, "edit-copy"),

    /**
     * edit-cut: The icon for the cut action.
     */
    EDIT_CUT(PThemeIconContextName.ACTIONS, "edit-cut"),

    /**
     * edit-delete: The icon for the delete action.
     */
    EDIT_DELETE(PThemeIconContextName.ACTIONS, "edit-delete"),

    /**
     * edit-find: The icon for the find action.
     */
    EDIT_FIND(PThemeIconContextName.ACTIONS, "edit-find"),

    /**
     * edit-find-replace: The icon for the find and replace action.
     */
    EDIT_FIND_REPLACE(PThemeIconContextName.ACTIONS, "edit-find-replace"),

    /**
     * edit-paste: The icon for the paste action.
     */
    EDIT_PASTE(PThemeIconContextName.ACTIONS, "edit-paste"),

    /**
     * edit-redo: The icon for the redo action.
     */
    EDIT_REDO(PThemeIconContextName.ACTIONS, "edit-redo"),

    /**
     * edit-select-all: The icon for the select all action.
     */
    EDIT_SELECT_ALL(PThemeIconContextName.ACTIONS, "edit-select-all"),

    /**
     * edit-undo: The icon for the undo action.
     */
    EDIT_UNDO(PThemeIconContextName.ACTIONS, "edit-undo"),

    /**
     * folder-new: The icon for creating a new folder.
     */
    FOLDER_NEW(PThemeIconContextName.ACTIONS, "folder-new"),

    /**
     * format-indent-less: The icon for the decrease indent formatting action.
     */
    FORMAT_INDENT_LESS(PThemeIconContextName.ACTIONS, "format-indent-less"),

    /**
     * format-indent-more: The icon for the increase indent formatting action.
     */
    FORMAT_INDENT_MORE(PThemeIconContextName.ACTIONS, "format-indent-more"),

    /**
     * format-justify-center: The icon for the center justification formatting action.
     */
    FORMAT_JUSTIFY_CENTER(PThemeIconContextName.ACTIONS, "format-justify-center"),

    /**
     * format-justify-fill: The icon for the fill justification formatting action.
     */
    FORMAT_JUSTIFY_FILL(PThemeIconContextName.ACTIONS, "format-justify-fill"),

    /**
     * format-justify-left: The icon for the left justification formatting action.
     */
    FORMAT_JUSTIFY_LEFT(PThemeIconContextName.ACTIONS, "format-justify-left"),

    /**
     * format-justify-right: The icon for the right justification action.
     */
    FORMAT_JUSTIFY_RIGHT(PThemeIconContextName.ACTIONS, "format-justify-right"),

    /**
     * format-text-direction-ltr: The icon for the left-to-right text formatting action.
     */
    FORMAT_TEXT_DIRECTION_LTR(PThemeIconContextName.ACTIONS, "format-text-direction-ltr"),

    /**
     * format-text-direction-rtl: The icon for the right-to-left formatting action.
     */
    FORMAT_TEXT_DIRECTION_RTL(PThemeIconContextName.ACTIONS, "format-text-direction-rtl"),

    /**
     * format-text-bold: The icon for the bold text formatting action.
     */
    FORMAT_TEXT_BOLD(PThemeIconContextName.ACTIONS, "format-text-bold"),

    /**
     * format-text-italic: The icon for the italic text formatting action.
     */
    FORMAT_TEXT_ITALIC(PThemeIconContextName.ACTIONS, "format-text-italic"),

    /**
     * format-text-underline: The icon for the underlined text formatting action.
     */
    FORMAT_TEXT_UNDERLINE(PThemeIconContextName.ACTIONS, "format-text-underline"),

    /**
     * format-text-strikethrough: The icon for the strikethrough text formatting action.
     */
    FORMAT_TEXT_STRIKETHROUGH(PThemeIconContextName.ACTIONS, "format-text-strikethrough"),

    /**
     * go-bottom: The icon for the go to bottom of a list action.
     */
    GO_BOTTOM(PThemeIconContextName.ACTIONS, "go-bottom"),

    /**
     * go-down: The icon for the go down in a list action.
     */
    GO_DOWN(PThemeIconContextName.ACTIONS, "go-down"),

    /**
     * go-first: The icon for the go to the first item in a list action.
     */
    GO_FIRST(PThemeIconContextName.ACTIONS, "go-first"),

    /**
     * go-home: The icon for the go to home location action.
     */
    GO_HOME(PThemeIconContextName.ACTIONS, "go-home"),

    /**
     * go-jump: The icon for the jump to action.
     */
    GO_JUMP(PThemeIconContextName.ACTIONS, "go-jump"),

    /**
     * go-last: The icon for the go to the last item in a list action.
     */
    GO_LAST(PThemeIconContextName.ACTIONS, "go-last"),

    /**
     * go-next: The icon for the go to the next item in a list action.
     */
    GO_NEXT(PThemeIconContextName.ACTIONS, "go-next"),

    /**
     * go-previous: The icon for the go to the previous item in a list action.
     */
    GO_PREVIOUS(PThemeIconContextName.ACTIONS, "go-previous"),

    /**
     * go-top: The icon for the go to the top of a list action.
     */
    GO_TOP(PThemeIconContextName.ACTIONS, "go-top"),

    /**
     * go-up: The icon for the go up in a list action.
     */
    GO_UP(PThemeIconContextName.ACTIONS, "go-up"),

    /**
     * help-about: The icon for the About item in the Help menu.
     */
    HELP_ABOUT(PThemeIconContextName.ACTIONS, "help-about"),

    /**
     * help-contents: The icon for Contents item in the Help menu.
     */
    HELP_CONTENTS(PThemeIconContextName.ACTIONS, "help-contents"),

    /**
     * help-faq: The icon for the FAQ item in the Help menu.
     */
    HELP_FAQ(PThemeIconContextName.ACTIONS, "help-faq"),

    /**
     * insert-image: The icon for the insert image action of an application.
     */
    INSERT_IMAGE(PThemeIconContextName.ACTIONS, "insert-image"),

    /**
     * insert-link: The icon for the insert link action of an application.
     */
    INSERT_LINK(PThemeIconContextName.ACTIONS, "insert-link"),

    /**
     * insert-object: The icon for the insert object action of an application.
     */
    INSERT_OBJECT(PThemeIconContextName.ACTIONS, "insert-object"),

    /**
     * insert-text: The icon for the insert text action of an application.
     */
    INSERT_TEXT(PThemeIconContextName.ACTIONS, "insert-text"),

    /**
     * list-add: The icon for the add to list action.
     */
    LIST_ADD(PThemeIconContextName.ACTIONS, "list-add"),

    /**
     * list-remove: The icon for the remove from list action.
     */
    LIST_REMOVE(PThemeIconContextName.ACTIONS, "list-remove"),

    /**
     * mail-forward: The icon for the forward action of an electronic mail application.
     */
    MAIL_FORWARD(PThemeIconContextName.ACTIONS, "mail-forward"),

    /**
     * mail-mark-important: The icon for the mark as important action of an electronic mail application.
     */
    MAIL_MARK_IMPORTANT(PThemeIconContextName.ACTIONS, "mail-mark-important"),

    /**
     * mail-mark-junk: The icon for the mark as junk action of an electronic mail application.
     */
    MAIL_MARK_JUNK(PThemeIconContextName.ACTIONS, "mail-mark-junk"),

    /**
     * mail-mark-notjunk: The icon for the mark as not junk action of an electronic mail application.
     */
    MAIL_MARK_NOTJUNK(PThemeIconContextName.ACTIONS, "mail-mark-notjunk"),

    /**
     * mail-mark-read: The icon for the mark as read action of an electronic mail application.
     */
    MAIL_MARK_READ(PThemeIconContextName.ACTIONS, "mail-mark-read"),

    /**
     * mail-mark-unread: The icon for the mark as unread action of an electronic mail application.
     */
    MAIL_MARK_UNREAD(PThemeIconContextName.ACTIONS, "mail-mark-unread"),

    /**
     * mail-message-new: The icon for the compose new mail action of an electronic mail application.
     */
    MAIL_MESSAGE_NEW(PThemeIconContextName.ACTIONS, "mail-message-new"),

    /**
     * mail-reply-all: The icon for the reply to all action of an electronic mail application.
     */
    MAIL_REPLY_ALL(PThemeIconContextName.ACTIONS, "mail-reply-all"),

    /**
     * mail-reply-sender: The icon for the reply to sender action of an electronic mail application.
     */
    MAIL_REPLY_SENDER(PThemeIconContextName.ACTIONS, "mail-reply-sender"),

    /**
     * mail-send: The icon for the send action of an electronic mail application.
     */
    MAIL_SEND(PThemeIconContextName.ACTIONS, "mail-send"),

    /**
     * mail-send-receive: The icon for the send and receive action of an electronic mail application.
     */
    MAIL_SEND_RECEIVE(PThemeIconContextName.ACTIONS, "mail-send-receive"),

    /**
     * media-eject: The icon for the eject action of a media player or file manager.
     */
    MEDIA_EJECT(PThemeIconContextName.ACTIONS, "media-eject"),

    /**
     * media-playback-pause: The icon for the pause action of a media player.
     */
    MEDIA_PLAYBACK_PAUSE(PThemeIconContextName.ACTIONS, "media-playback-pause"),

    /**
     * media-playback-start: The icon for the start playback action of a media player.
     */
    MEDIA_PLAYBACK_START(PThemeIconContextName.ACTIONS, "media-playback-start"),

    /**
     * media-playback-stop: The icon for the stop action of a media player.
     */
    MEDIA_PLAYBACK_STOP(PThemeIconContextName.ACTIONS, "media-playback-stop"),

    /**
     * media-record: The icon for the record action of a media application.
     */
    MEDIA_RECORD(PThemeIconContextName.ACTIONS, "media-record"),

    /**
     * media-seek-backward: The icon for the seek backward action of a media player.
     */
    MEDIA_SEEK_BACKWARD(PThemeIconContextName.ACTIONS, "media-seek-backward"),

    /**
     * media-seek-forward: The icon for the seek forward action of a media player.
     */
    MEDIA_SEEK_FORWARD(PThemeIconContextName.ACTIONS, "media-seek-forward"),

    /**
     * media-skip-backward: The icon for the skip backward action of a media player.
     */
    MEDIA_SKIP_BACKWARD(PThemeIconContextName.ACTIONS, "media-skip-backward"),

    /**
     * media-skip-forward: The icon for the skip forward action of a media player.
     */
    MEDIA_SKIP_FORWARD(PThemeIconContextName.ACTIONS, "media-skip-forward"),

    /**
     * object-flip-horizontal: The icon for the action to flip an object horizontally.
     */
    OBJECT_FLIP_HORIZONTAL(PThemeIconContextName.ACTIONS, "object-flip-horizontal"),

    /**
     * object-flip-vertical: The icon for the action to flip an object vertically.
     */
    OBJECT_FLIP_VERTICAL(PThemeIconContextName.ACTIONS, "object-flip-vertical"),

    /**
     * object-rotate-left: The icon for the rotate left action performed on an object.
     */
    OBJECT_ROTATE_LEFT(PThemeIconContextName.ACTIONS, "object-rotate-left"),

    /**
     * object-rotate-right: The icon for the rotate rigt action performed on an object.
     */
    OBJECT_ROTATE_RIGHT(PThemeIconContextName.ACTIONS, "object-rotate-right"),

    /**
     * process-stop: The icon used for the “Stop” action in applications with actions that may take a while to process, such as web page loading in a browser.
     */
    PROCESS_STOP(PThemeIconContextName.ACTIONS, "process-stop"),

    /**
     * system-lock-screen: The icon used for the “Lock Screen” item in the desktop's panel application.
     */
    SYSTEM_LOCK_SCREEN(PThemeIconContextName.ACTIONS, "system-lock-screen"),

    /**
     * system-log-out: The icon used for the “Log Out” item in the desktop's panel application.
     */
    SYSTEM_LOG_OUT(PThemeIconContextName.ACTIONS, "system-log-out"),

    /**
     * system-run: The icon used for the “Run Application...” item in the desktop's panel application.
     */
    SYSTEM_RUN(PThemeIconContextName.ACTIONS, "system-run"),

    /**
     * system-search: The icon used for the “Search” item in the desktop's panel application.
     */
    SYSTEM_SEARCH(PThemeIconContextName.ACTIONS, "system-search"),

    /**
     * system-reboot: The icon used for the “Reboot” item in the desktop's panel application.
     */
    SYSTEM_REBOOT(PThemeIconContextName.ACTIONS, "system-reboot"),

    /**
     * system-shutdown: The icon used for the “Shutdown” item in the desktop's panel application.
     */
    SYSTEM_SHUTDOWN(PThemeIconContextName.ACTIONS, "system-shutdown"),

    /**
     * tools-check-spelling: The icon used for the “Check Spelling” item in the application's “Tools” menu.
     */
    TOOLS_CHECK_SPELLING(PThemeIconContextName.ACTIONS, "tools-check-spelling"),

    /**
     * view-fullscreen: The icon used for the “Fullscreen” item in the application's “View” menu.
     */
    VIEW_FULLSCREEN(PThemeIconContextName.ACTIONS, "view-fullscreen"),

    /**
     * view-refresh: The icon used for the “Refresh” item in the application's “View” menu.
     */
    VIEW_REFRESH(PThemeIconContextName.ACTIONS, "view-refresh"),

    /**
     * view-restore: The icon used by an application for leaving the fullscreen view, and returning to a normal windowed view.
     */
    VIEW_RESTORE(PThemeIconContextName.ACTIONS, "view-restore"),

    /**
     * view-sort-ascending: The icon used for the “Sort Ascending” item in the application's “View” menu, or in a button for changing the sort method for a list.
     */
    VIEW_SORT_ASCENDING(PThemeIconContextName.ACTIONS, "view-sort-ascending"),

    /**
     * view-sort-descending: The icon used for the “Sort Descending” item in the application's “View” menu, or in a button for changing the sort method for a list.
     */
    VIEW_SORT_DESCENDING(PThemeIconContextName.ACTIONS, "view-sort-descending"),

    /**
     * window-close: The icon used for the “Close Window” item in the application's “Windows” menu.
     */
    WINDOW_CLOSE(PThemeIconContextName.ACTIONS, "window-close"),

    /**
     * window-new: The icon used for the “New Window” item in the application's “Windows” menu.
     */
    WINDOW_NEW(PThemeIconContextName.ACTIONS, "window-new"),

    /**
     * zoom-fit-best: The icon used for the “Best Fit” item in the application's “View” menu.
     */
    ZOOM_FIT_BEST(PThemeIconContextName.ACTIONS, "zoom-fit-best"),

    /**
     * zoom-in: The icon used for the “Zoom in” item in the application's “View” menu.
     */
    ZOOM_IN(PThemeIconContextName.ACTIONS, "zoom-in"),

    /**
     * zoom-original: The icon used for the “Original Size” item in the application's “View” menu.
     */
    ZOOM_ORIGINAL(PThemeIconContextName.ACTIONS, "zoom-original"),

    /**
     * zoom-out: The icon used for the “Zoom Out” item in the application's “View” menu.
     */
    ZOOM_OUT(PThemeIconContextName.ACTIONS, "zoom-out"),

    /**
     * process-working: This is the standard spinner animation for web browsers and file managers to show that the location is loading.
     */
    PROCESS_WORKING(PThemeIconContextName.ANIMATIONS, "process-working"),

    /**
     * accessories-calculator: The icon used for the desktop's calculator accessory program.
     */
    ACCESSORIES_CALCULATOR(PThemeIconContextName.APPLICATIONS, "accessories-calculator"),

    /**
     * accessories-character-map: The icon used for the desktop's international and extended text character accessory program.
     */
    ACCESSORIES_CHARACTER_MAP(PThemeIconContextName.APPLICATIONS, "accessories-character-map"),

    /**
     * accessories-dictionary: The icon used for the desktop's dictionary accessory program.
     */
    ACCESSORIES_DICTIONARY(PThemeIconContextName.APPLICATIONS, "accessories-dictionary"),

    /**
     * accessories-text-editor: The icon used for the desktop's text editing accessory program.
     */
    ACCESSORIES_TEXT_EDITOR(PThemeIconContextName.APPLICATIONS, "accessories-text-editor"),

    /**
     * help-browser: The icon used for the desktop's help browsing application.
     */
    HELP_BROWSER(PThemeIconContextName.APPLICATIONS, "help-browser"),

    /**
     * multimedia-volume-control: The icon used for the desktop's hardware volume control application.
     */
    MULTIMEDIA_VOLUME_CONTROL(PThemeIconContextName.APPLICATIONS, "multimedia-volume-control"),

    /**
     * preferences-desktop-accessibility: The icon used for the desktop's accessibility preferences.
     */
    PREFERENCES_DESKTOP_ACCESSIBILITY(PThemeIconContextName.APPLICATIONS, "preferences-desktop-accessibility"),

    /**
     * preferences-desktop-font: The icon used for the desktop's font preferences.
     */
    PREFERENCES_DESKTOP_FONT(PThemeIconContextName.APPLICATIONS, "preferences-desktop-font"),

    /**
     * preferences-desktop-keyboard: The icon used for the desktop's keyboard preferences.
     */
    PREFERENCES_DESKTOP_KEYBOARD(PThemeIconContextName.APPLICATIONS, "preferences-desktop-keyboard"),

    /**
     * preferences-desktop-locale: The icon used for the desktop's locale preferences.
     */
    PREFERENCES_DESKTOP_LOCALE(PThemeIconContextName.APPLICATIONS, "preferences-desktop-locale"),

    /**
     * preferences-desktop-multimedia: The icon used for the desktop's multimedia preferences.
     */
    PREFERENCES_DESKTOP_MULTIMEDIA(PThemeIconContextName.APPLICATIONS, "preferences-desktop-multimedia"),

    /**
     * preferences-desktop-screensaver: The icon used for the desktop's screen saving preferences.
     */
    PREFERENCES_DESKTOP_SCREENSAVER(PThemeIconContextName.APPLICATIONS, "preferences-desktop-screensaver"),

    /**
     * preferences-desktop-theme: The icon used for the desktop's theme preferences.
     */
    PREFERENCES_DESKTOP_THEME(PThemeIconContextName.APPLICATIONS, "preferences-desktop-theme"),

    /**
     * preferences-desktop-wallpaper: The icon used for the desktop's wallpaper preferences.
     */
    PREFERENCES_DESKTOP_WALLPAPER(PThemeIconContextName.APPLICATIONS, "preferences-desktop-wallpaper"),

    /**
     * system-file-manager: The icon used for the desktop's file management application.
     */
    SYSTEM_FILE_MANAGER(PThemeIconContextName.APPLICATIONS, "system-file-manager"),

    /**
     * system-software-install: The icon used for the desktop's software installer application.
     */
    SYSTEM_SOFTWARE_INSTALL(PThemeIconContextName.APPLICATIONS, "system-software-install"),

    /**
     * system-software-update: The icon used for the desktop's software updating application.
     */
    SYSTEM_SOFTWARE_UPDATE(PThemeIconContextName.APPLICATIONS, "system-software-update"),

    /**
     * utilities-system-monitor: The icon used for the desktop's system resource monitor application.
     */
    UTILITIES_SYSTEM_MONITOR(PThemeIconContextName.APPLICATIONS, "utilities-system-monitor"),

    /**
     * utilities-terminal: The icon used for the desktop's terminal emulation application.
     */
    UTILITIES_TERMINAL(PThemeIconContextName.APPLICATIONS, "utilities-terminal"),

    /**
     * applications-accessories: The icon for the “Accessories” sub-menu of the Programs menu.
     */
    APPLICATIONS_ACCESSORIES(PThemeIconContextName.CATEGORIES, "applications-accessories"),

    /**
     * applications-development: The icon for the “Programming” sub-menu of the Programs menu.
     */
    APPLICATIONS_DEVELOPMENT(PThemeIconContextName.CATEGORIES, "applications-development"),

    /**
     * applications-engineering: The icon for the “Engineering” sub-menu of the Programs menu.
     */
    APPLICATIONS_ENGINEERING(PThemeIconContextName.CATEGORIES, "applications-engineering"),

    /**
     * applications-games: The icon for the “Games” sub-menu of the Programs menu.
     */
    APPLICATIONS_GAMES(PThemeIconContextName.CATEGORIES, "applications-games"),

    /**
     * applications-graphics: The icon for the “Graphics” sub-menu of the Programs menu.
     */
    APPLICATIONS_GRAPHICS(PThemeIconContextName.CATEGORIES, "applications-graphics"),

    /**
     * applications-internet: The icon for the “Internet” sub-menu of the Programs menu.
     */
    APPLICATIONS_INTERNET(PThemeIconContextName.CATEGORIES, "applications-internet"),

    /**
     * applications-multimedia: The icon for the “Multimedia” sub-menu of the Programs menu.
     */
    APPLICATIONS_MULTIMEDIA(PThemeIconContextName.CATEGORIES, "applications-multimedia"),

    /**
     * applications-office: The icon for the “Office” sub-menu of the Programs menu.
     */
    APPLICATIONS_OFFICE(PThemeIconContextName.CATEGORIES, "applications-office"),

    /**
     * applications-other: The icon for the “Other” sub-menu of the Programs menu.
     */
    APPLICATIONS_OTHER(PThemeIconContextName.CATEGORIES, "applications-other"),

    /**
     * applications-science: The icon for the “Science” sub-menu of the Programs menu.
     */
    APPLICATIONS_SCIENCE(PThemeIconContextName.CATEGORIES, "applications-science"),

    /**
     * applications-system: The icon for the “System Tools” sub-menu of the Programs menu.
     */
    APPLICATIONS_SYSTEM(PThemeIconContextName.CATEGORIES, "applications-system"),

    /**
     * applications-utilities: The icon for the “Utilities” sub-menu of the Programs menu.
     */
    APPLICATIONS_UTILITIES(PThemeIconContextName.CATEGORIES, "applications-utilities"),

    /**
     * preferences-desktop: The icon for the “Desktop Preferences” category.
     */
    PREFERENCES_DESKTOP(PThemeIconContextName.CATEGORIES, "preferences-desktop"),

    /**
     * preferences-desktop-peripherals: The icon for the “Peripherals” sub-category of the “Desktop Preferences” category.
     */
    PREFERENCES_DESKTOP_PERIPHERALS(PThemeIconContextName.CATEGORIES, "preferences-desktop-peripherals"),

    /**
     * preferences-desktop-personal: The icon for the “Personal” sub-category of the “Desktop Preferences” category.
     */
    PREFERENCES_DESKTOP_PERSONAL(PThemeIconContextName.CATEGORIES, "preferences-desktop-personal"),

    /**
     * preferences-other: The icon for the “Other” preferences category.
     */
    PREFERENCES_OTHER(PThemeIconContextName.CATEGORIES, "preferences-other"),

    /**
     * preferences-system: The icon for the “System Preferences” category.
     */
    PREFERENCES_SYSTEM(PThemeIconContextName.CATEGORIES, "preferences-system"),

    /**
     * preferences-system-network: The icon for the “Network” sub-category of the “System Preferences” category.
     */
    PREFERENCES_SYSTEM_NETWORK(PThemeIconContextName.CATEGORIES, "preferences-system-network"),

    /**
     * system-help: The icon for the “Help” system category.
     */
    SYSTEM_HELP(PThemeIconContextName.CATEGORIES, "system-help"),

    /**
     * audio-card: The icon used for the audio rendering device.
     */
    AUDIO_CARD(PThemeIconContextName.DEVICES, "audio-card"),

    /**
     * audio-input-microphone: The icon used for the microphone audio input device.
     */
    AUDIO_INPUT_MICROPHONE(PThemeIconContextName.DEVICES, "audio-input-microphone"),

    /**
     * battery: The icon used for the system battery device.
     */
    BATTERY(PThemeIconContextName.DEVICES, "battery"),

    /**
     * camera-photo: The icon used for a digital still camera devices.
     */
    CAMERA_PHOTO(PThemeIconContextName.DEVICES, "camera-photo"),

    /**
     * camera-video: The fallback icon for video cameras.
     */
    CAMERA_VIDEO(PThemeIconContextName.DEVICES, "camera-video"),

    /**
     * camera-web: The fallback icon for web cameras.
     */
    CAMERA_WEB(PThemeIconContextName.DEVICES, "camera-web"),

    /**
     * computer: The icon used for the computing device as a whole.
     */
    COMPUTER(PThemeIconContextName.DEVICES, "computer"),

    /**
     * drive-harddisk: The icon used for hard disk drives.
     */
    DRIVE_HARDDISK(PThemeIconContextName.DEVICES, "drive-harddisk"),

    /**
     * drive-optical: The icon used for optical media drives such as CD and DVD.
     */
    DRIVE_OPTICAL(PThemeIconContextName.DEVICES, "drive-optical"),

    /**
     * drive-removable-media: The icon used for removable media drives.
     */
    DRIVE_REMOVABLE_MEDIA(PThemeIconContextName.DEVICES, "drive-removable-media"),

    /**
     * input-gaming: The icon used for the gaming input device.
     */
    INPUT_GAMING(PThemeIconContextName.DEVICES, "input-gaming"),

    /**
     * input-keyboard: The icon used for the keyboard input device.
     */
    INPUT_KEYBOARD(PThemeIconContextName.DEVICES, "input-keyboard"),

    /**
     * input-mouse: The icon used for the mousing input device.
     */
    INPUT_MOUSE(PThemeIconContextName.DEVICES, "input-mouse"),

    /**
     * input-tablet: The icon used for graphics tablet input devices.
     */
    INPUT_TABLET(PThemeIconContextName.DEVICES, "input-tablet"),

    /**
     * media-flash: The fallback icon used for flash media, such as memory stick and SD.
     */
    MEDIA_FLASH(PThemeIconContextName.DEVICES, "media-flash"),

    /**
     * media-floppy: The icon used for physical floppy disk media.
     */
    MEDIA_FLOPPY(PThemeIconContextName.DEVICES, "media-floppy"),

    /**
     * media-optical: The icon used for physical optical media such as CD and DVD.
     */
    MEDIA_OPTICAL(PThemeIconContextName.DEVICES, "media-optical"),

    /**
     * media-tape: The icon used for generic physical tape media.
     */
    MEDIA_TAPE(PThemeIconContextName.DEVICES, "media-tape"),

    /**
     * modem: The icon used for modem devices.
     */
    MODEM(PThemeIconContextName.DEVICES, "modem"),

    /**
     * multimedia-player: The icon used for generic multimedia playing devices.
     */
    MULTIMEDIA_PLAYER(PThemeIconContextName.DEVICES, "multimedia-player"),

    /**
     * network-wired: The icon used for wired network connections.
     */
    NETWORK_WIRED(PThemeIconContextName.DEVICES, "network-wired"),

    /**
     * network-wireless: The icon used for wireless network connections.
     */
    NETWORK_WIRELESS(PThemeIconContextName.DEVICES, "network-wireless"),

    /**
     * pda: This is the fallback icon for Personal Digial Assistant devices. Primary use of this icon is for PDA devices connected to the PC. Connection medium is not an important aspect of the icon. The metaphor for this fallback icon should be a generic PDA device icon.
     */
    PDA(PThemeIconContextName.DEVICES, "pda"),

    /**
     * phone: This is the default fallback for phone devices. Primary use of this icon group is for phone devices which support connectivity to the PC. These may be VoIP, cellular, or possibly landline phones. The metaphor for this fallback should be a generic mobile phone device.
     */
    PHONE(PThemeIconContextName.DEVICES, "phone"),

    /**
     * printer: The icon used for a printer device.
     */
    PRINTER(PThemeIconContextName.DEVICES, "printer"),

    /**
     * scanner: The icon used for a scanner device.
     */
    SCANNER(PThemeIconContextName.DEVICES, "scanner"),

    /**
     * video-display: The icon used for the monitor that video gets displayed to.
     */
    VIDEO_DISPLAY(PThemeIconContextName.DEVICES, "video-display"),

    /**
     * emblem-default: The icon used as an emblem to specify the default selection of a printer for example.
     */
    EMBLEM_DEFAULT(PThemeIconContextName.EMBLEMS, "emblem-default"),

    /**
     * emblem-documents: The icon used as an emblem for the directory where a user's documents are stored.
     */
    EMBLEM_DOCUMENTS(PThemeIconContextName.EMBLEMS, "emblem-documents"),

    /**
     * emblem-downloads: The icon used as an emblem for the directory where a user's downloads from the internet are stored.
     */
    EMBLEM_DOWNLOADS(PThemeIconContextName.EMBLEMS, "emblem-downloads"),

    /**
     * emblem-favorite: The icon used as an emblem for files and directories that the user marks as favorites.
     */
    EMBLEM_FAVORITE(PThemeIconContextName.EMBLEMS, "emblem-favorite"),

    /**
     * emblem-important: The icon used as an emblem for files and directories that are marked as important by the user.
     */
    EMBLEM_IMPORTANT(PThemeIconContextName.EMBLEMS, "emblem-important"),

    /**
     * emblem-mail: The icon used as an emblem to specify the directory where the user's electronic mail is stored.
     */
    EMBLEM_MAIL(PThemeIconContextName.EMBLEMS, "emblem-mail"),

    /**
     * emblem-photos: The icon used as an emblem to specify the directory where the user stores photographs.
     */
    EMBLEM_PHOTOS(PThemeIconContextName.EMBLEMS, "emblem-photos"),

    /**
     * emblem-readonly: The icon used as an emblem for files and directories which can not be written to by the user.
     */
    EMBLEM_READONLY(PThemeIconContextName.EMBLEMS, "emblem-readonly"),

    /**
     * emblem-shared: The icon used as an emblem for files and directories that are shared to other users.
     */
    EMBLEM_SHARED(PThemeIconContextName.EMBLEMS, "emblem-shared"),

    /**
     * emblem-symbolic-link: The icon used as an emblem for files and direcotires that are links to other files or directories on the filesystem.
     */
    EMBLEM_SYMBOLIC_LINK(PThemeIconContextName.EMBLEMS, "emblem-symbolic-link"),

    /**
     * emblem-synchronized: The icon used as an emblem for files or directories that are configured to be synchronized to another device.
     */
    EMBLEM_SYNCHRONIZED(PThemeIconContextName.EMBLEMS, "emblem-synchronized"),

    /**
     * emblem-system: The icon used as an emblem for directories that contain system libraries, settings, and data.
     */
    EMBLEM_SYSTEM(PThemeIconContextName.EMBLEMS, "emblem-system"),

    /**
     * emblem-unreadable: The icon used as an emblem for files and directories that are inaccessible.
     */
    EMBLEM_UNREADABLE(PThemeIconContextName.EMBLEMS, "emblem-unreadable"),

    /**
     * face-angel: The icon used for the 0:-) emote.
     */
    FACE_ANGEL(PThemeIconContextName.EMOTES, "face-angel"),

    /**
     * face-angry: The icon used for the X-( emote.
     */
    FACE_ANGRY(PThemeIconContextName.EMOTES, "face-angry"),

    /**
     * face-cool: The icon used for the B-) emote.
     */
    FACE_COOL(PThemeIconContextName.EMOTES, "face-cool"),

    /**
     * face-crying: The icon used for the :'( emote.
     */
    FACE_CRYING(PThemeIconContextName.EMOTES, "face-crying"),

    /**
     * face-devilish: The icon used for the >:-) emote.
     */
    FACE_DEVILISH(PThemeIconContextName.EMOTES, "face-devilish"),

    /**
     * face-embarrassed: The icon used for the :-[ emote.
     */
    FACE_EMBARRASSED(PThemeIconContextName.EMOTES, "face-embarrassed"),

    /**
     * face-kiss: The icon used for the :-* emote.
     */
    FACE_KISS(PThemeIconContextName.EMOTES, "face-kiss"),

    /**
     * face-laugh: The icon used for the :-)) emote.
     */
    FACE_LAUGH(PThemeIconContextName.EMOTES, "face-laugh"),

    /**
     * face-monkey: The icon used for the :-(|) emote.
     */
    FACE_MONKEY(PThemeIconContextName.EMOTES, "face-monkey"),

    /**
     * face-plain: The icon used for the :-| emote.
     */
    FACE_PLAIN(PThemeIconContextName.EMOTES, "face-plain"),

    /**
     * face-raspberry: The icon used for the :-P emote.
     */
    FACE_RASPBERRY(PThemeIconContextName.EMOTES, "face-raspberry"),

    /**
     * face-sad: The icon used for the :-( emote.
     */
    FACE_SAD(PThemeIconContextName.EMOTES, "face-sad"),

    /**
     * face-sick: The icon used for the :-& emote.
     */
    FACE_SICK(PThemeIconContextName.EMOTES, "face-sick"),

    /**
     * face-smile: The icon used for the :-) emote.
     */
    FACE_SMILE(PThemeIconContextName.EMOTES, "face-smile"),

    /**
     * face-smile-big: The icon used for the :-D emote.
     */
    FACE_SMILE_BIG(PThemeIconContextName.EMOTES, "face-smile-big"),

    /**
     * face-smirk: The icon used for the :-! emote.
     */
    FACE_SMIRK(PThemeIconContextName.EMOTES, "face-smirk"),

    /**
     * face-surprise: The icon used for the :-0 emote.
     */
    FACE_SURPRISE(PThemeIconContextName.EMOTES, "face-surprise"),

    /**
     * face-tired: The icon used for the |-) emote.
     */
    FACE_TIRED(PThemeIconContextName.EMOTES, "face-tired"),

    /**
     * face-uncertain: The icon used for the :-/ emote.
     */
    FACE_UNCERTAIN(PThemeIconContextName.EMOTES, "face-uncertain"),

    /**
     * face-wink: The icon used for the ;-) emote.
     */
    FACE_WINK(PThemeIconContextName.EMOTES, "face-wink"),

    /**
     * face-worried: The icon used for the :-S emote.
     */
    FACE_WORRIED(PThemeIconContextName.EMOTES, "face-worried"),

    /**
     * application-x-executable: The icon used for executable file types.
     */
    APPLICATION_X_EXECUTABLE(PThemeIconContextName.MIMETYPES, "application-x-executable"),

    /**
     * audio-x-generic: The icon used for generic audio file types.
     */
    AUDIO_X_GENERIC(PThemeIconContextName.MIMETYPES, "audio-x-generic"),

    /**
     * font-x-generic: The icon used for generic font file types.
     */
    FONT_X_GENERIC(PThemeIconContextName.MIMETYPES, "font-x-generic"),

    /**
     * image-x-generic: The icon used for generic image file types.
     */
    IMAGE_X_GENERIC(PThemeIconContextName.MIMETYPES, "image-x-generic"),

    /**
     * package-x-generic: The icon used for generic package file types.
     */
    PACKAGE_X_GENERIC(PThemeIconContextName.MIMETYPES, "package-x-generic"),

    /**
     * text-html: The icon used for HTML text file types.
     */
    TEXT_HTML(PThemeIconContextName.MIMETYPES, "text-html"),

    /**
     * text-x-generic: The icon used for generic text file types.
     */
    TEXT_X_GENERIC(PThemeIconContextName.MIMETYPES, "text-x-generic"),

    /**
     * text-x-generic-template: The icon used for generic text templates.
     */
    TEXT_X_GENERIC_TEMPLATE(PThemeIconContextName.MIMETYPES, "text-x-generic-template"),

    /**
     * text-x-script: The icon used for script file types, such as shell scripts.
     */
    TEXT_X_SCRIPT(PThemeIconContextName.MIMETYPES, "text-x-script"),

    /**
     * video-x-generic: The icon used for generic video file types.
     */
    VIDEO_X_GENERIC(PThemeIconContextName.MIMETYPES, "video-x-generic"),

    /**
     * x-office-address-book: The icon used for generic address book file types.
     */
    X_OFFICE_ADDRESS_BOOK(PThemeIconContextName.MIMETYPES, "x-office-address-book"),

    /**
     * x-office-calendar: The icon used for generic calendar file types.
     */
    X_OFFICE_CALENDAR(PThemeIconContextName.MIMETYPES, "x-office-calendar"),

    /**
     * x-office-document: The icon used for generic document and letter file types.
     */
    X_OFFICE_DOCUMENT(PThemeIconContextName.MIMETYPES, "x-office-document"),

    /**
     * x-office-presentation: The icon used for generic presentation file types.
     */
    X_OFFICE_PRESENTATION(PThemeIconContextName.MIMETYPES, "x-office-presentation"),

    /**
     * x-office-spreadsheet: The icon used for generic spreadsheet file types.
     */
    X_OFFICE_SPREADSHEET(PThemeIconContextName.MIMETYPES, "x-office-spreadsheet"),

    /**
     * folder: The standard folder icon used to represent directories on local filesystems, mail folders, and other hierarchical groups.
     */
    FOLDER(PThemeIconContextName.PLACES, "folder"),

    /**
     * folder-remote: The icon used for normal directories on a remote filesystem.
     */
    FOLDER_REMOTE(PThemeIconContextName.PLACES, "folder-remote"),

    /**
     * network-server: The icon used for individual host machines under the “Network Servers” place in the file manager.
     */
    NETWORK_SERVER(PThemeIconContextName.PLACES, "network-server"),

    /**
     * network-workgroup: The icon for the “Network Servers” place in the desktop's file manager, and workgroups within the network.
     */
    NETWORK_WORKGROUP(PThemeIconContextName.PLACES, "network-workgroup"),

    /**
     * start-here: The icon used by the desktop's main menu for accessing places, applications, and other features.
     */
    START_HERE(PThemeIconContextName.PLACES, "start-here"),

    /**
     * user-bookmarks: The icon for the user's special “Bookmarks” place.
     */
    USER_BOOKMARKS(PThemeIconContextName.PLACES, "user-bookmarks"),

    /**
     * user-desktop: The icon for the special “Desktop” directory of the user.
     */
    USER_DESKTOP(PThemeIconContextName.PLACES, "user-desktop"),

    /**
     * user-home: The icon for the special “Home” directory of the user.
     */
    USER_HOME(PThemeIconContextName.PLACES, "user-home"),

    /**
     * user-trash: The icon for the user's “Trash” place in the desktop's file manager.
     */
    USER_TRASH(PThemeIconContextName.PLACES, "user-trash"),

    /**
     * appointment-missed: The icon used when an appointment was missed.
     */
    APPOINTMENT_MISSED(PThemeIconContextName.STATUS, "appointment-missed"),

    /**
     * appointment-soon: The icon used when an appointment will occur soon.
     */
    APPOINTMENT_SOON(PThemeIconContextName.STATUS, "appointment-soon"),

    /**
     * audio-volume-high: The icon used to indicate high audio volume.
     */
    AUDIO_VOLUME_HIGH(PThemeIconContextName.STATUS, "audio-volume-high"),

    /**
     * audio-volume-low: The icon used to indicate low audio volume.
     */
    AUDIO_VOLUME_LOW(PThemeIconContextName.STATUS, "audio-volume-low"),

    /**
     * audio-volume-medium: The icon used to indicate medium audio volume.
     */
    AUDIO_VOLUME_MEDIUM(PThemeIconContextName.STATUS, "audio-volume-medium"),

    /**
     * audio-volume-muted: The icon used to indicate the muted state for audio playback.
     */
    AUDIO_VOLUME_MUTED(PThemeIconContextName.STATUS, "audio-volume-muted"),

    /**
     * battery-caution: The icon used when the battery is below 40%.
     */
    BATTERY_CAUTION(PThemeIconContextName.STATUS, "battery-caution"),

    /**
     * battery-low: The icon used when the battery is below 20%.
     */
    BATTERY_LOW(PThemeIconContextName.STATUS, "battery-low"),

    /**
     * dialog-error: The icon used when a dialog is opened to explain an error condition to the user.
     */
    DIALOG_ERROR(PThemeIconContextName.STATUS, "dialog-error"),

    /**
     * dialog-information: The icon used when a dialog is opened to give information to the user that may be pertinent to the requested action.
     */
    DIALOG_INFORMATION(PThemeIconContextName.STATUS, "dialog-information"),

    /**
     * dialog-password: The icon used when a dialog requesting the authentication credentials for a user is opened.
     */
    DIALOG_PASSWORD(PThemeIconContextName.STATUS, "dialog-password"),

    /**
     * dialog-question: The icon used when a dialog is opened to ask a simple question of the user.
     */
    DIALOG_QUESTION(PThemeIconContextName.STATUS, "dialog-question"),

    /**
     * dialog-warning: The icon used when a dialog is opened to warn the user of impending issues with the requested action.
     */
    DIALOG_WARNING(PThemeIconContextName.STATUS, "dialog-warning"),

    /**
     * folder-drag-accept: The icon used for a folder while an object is being dragged onto it, that is of a type that the directory can contain.
     */
    FOLDER_DRAG_ACCEPT(PThemeIconContextName.STATUS, "folder-drag-accept"),

    /**
     * folder-open: The icon used for folders, while their contents are being displayed within the same window. This icon would normally be shown in a tree or list view, next to the main view of a folder's contents.
     */
    FOLDER_OPEN(PThemeIconContextName.STATUS, "folder-open"),

    /**
     * folder-visiting: The icon used for folders, while their contents are being displayed in another window. This icon would typically be used when using multiple windows to navigate the hierarchy, such as in Nautilus's spatial mode.
     */
    FOLDER_VISITING(PThemeIconContextName.STATUS, "folder-visiting"),

    /**
     * image-loading: The icon used when another image is being loaded, such as thumnails for larger images in the file manager.
     */
    IMAGE_LOADING(PThemeIconContextName.STATUS, "image-loading"),

    /**
     * image-missing: The icon used when another image could not be loaded.
     */
    IMAGE_MISSING(PThemeIconContextName.STATUS, "image-missing"),

    /**
     * mail-attachment: The icon used for an electronic mail that contains attachments.
     */
    MAIL_ATTACHMENT(PThemeIconContextName.STATUS, "mail-attachment"),

    /**
     * mail-unread: The icon used for an electronic mail that is unread.
     */
    MAIL_UNREAD(PThemeIconContextName.STATUS, "mail-unread"),

    /**
     * mail-read: The icon used for an electronic mail that is read.
     */
    MAIL_READ(PThemeIconContextName.STATUS, "mail-read"),

    /**
     * mail-replied: The icon used for an electronic mail that has been replied to.
     */
    MAIL_REPLIED(PThemeIconContextName.STATUS, "mail-replied"),

    /**
     * mail-signed: The icon used for an electronic mail that contains a signature.
     */
    MAIL_SIGNED(PThemeIconContextName.STATUS, "mail-signed"),

    /**
     * mail-signed-verified: The icon used for an electronic mail that contains a signature which has also been verified by the security system.
     */
    MAIL_SIGNED_VERIFIED(PThemeIconContextName.STATUS, "mail-signed-verified"),

    /**
     * media-playlist-repeat: The icon for the repeat mode of a media player.
     */
    MEDIA_PLAYLIST_REPEAT(PThemeIconContextName.STATUS, "media-playlist-repeat"),

    /**
     * media-playlist-shuffle: The icon for the shuffle mode of a media player.
     */
    MEDIA_PLAYLIST_SHUFFLE(PThemeIconContextName.STATUS, "media-playlist-shuffle"),

    /**
     * network-error: The icon used when an error occurs trying to intialize the network connection of the computing device. This icon should be two computers, one in the background, with the screens of both computers, colored black, and with the theme's style element for errors, overlayed on top of the icon.
     */
    NETWORK_ERROR(PThemeIconContextName.STATUS, "network-error"),

    /**
     * network-idle: The icon used when no data is being transmitted or received, while the computing device is connected to a network. This icon should be two computers, one in the background, with the screens of both computers, colored black.
     */
    NETWORK_IDLE(PThemeIconContextName.STATUS, "network-idle"),

    /**
     * network-offline: The icon used when the computing device is disconnected from the network. This icon should be a computer in the background, with a screen colored black, and the theme's icon element to show that a device is not accessible, in the foreground.
     */
    NETWORK_OFFLINE(PThemeIconContextName.STATUS, "network-offline"),

    /**
     * network-receive: The icon used when data is being received, while the computing device is connected to a network. This icon should be two computers, one in the background, with its screen colored green, and the screen of the computer in the foreground, colored black.
     */
    NETWORK_RECEIVE(PThemeIconContextName.STATUS, "network-receive"),

    /**
     * network-transmit: The icon used when data is being transmitted, while the computing device is connected to a network. This icon should be two computers, one in the background, with its screen colored black, and the screen of the computer in the foreground, colored green.
     */
    NETWORK_TRANSMIT(PThemeIconContextName.STATUS, "network-transmit"),

    /**
     * network-transmit-receive: The icon used data is being both transmitted and received simultaneously, while the computing device is connected to a network. This icon should be two computers, one in the background, with the screens of both computers, colored green.
     */
    NETWORK_TRANSMIT_RECEIVE(PThemeIconContextName.STATUS, "network-transmit-receive"),

    /**
     * printer-error: The icon used when an error occurs while attempting to print. This icon should be the theme's printer device icon, with the theme's style element for errors, overlayed on top of the icon.
     */
    PRINTER_ERROR(PThemeIconContextName.STATUS, "printer-error"),

    /**
     * printer-printing: The icon used while a print job is successfully being spooled to a printing device. This icon should be the theme's printer device icon, with a document emerging from the printing device.
     */
    PRINTER_PRINTING(PThemeIconContextName.STATUS, "printer-printing"),

    /**
     * security-high: The icon used to indicate that the security level of a connection is known to be secure, using strong encryption and a valid certificate.
     */
    SECURITY_HIGH(PThemeIconContextName.STATUS, "security-high"),

    /**
     * security-medium: The icon used to indicate that the security level of a connection is presumed to be secure, using strong encryption, and a certificate that could not be automatically verified, but which the user has chosen to trust.
     */
    SECURITY_MEDIUM(PThemeIconContextName.STATUS, "security-medium"),

    /**
     * security-low: The icon used to indicate that the security level of a connection is presumed to be insecure, either by using weak encryption, or by using a certificate that the could not be automatically verified, and which the user has not chosent to trust.
     */
    SECURITY_LOW(PThemeIconContextName.STATUS, "security-low"),

    /**
     * software-update-available: The icon used when an update is available for software installed on the computing device, through the system software update program.
     */
    SOFTWARE_UPDATE_AVAILABLE(PThemeIconContextName.STATUS, "software-update-available"),

    /**
     * software-update-urgent: The icon used when an urgent update is available through the system software update program.
     */
    SOFTWARE_UPDATE_URGENT(PThemeIconContextName.STATUS, "software-update-urgent"),

    /**
     * sync-error: The icon used when an error occurs while attempting to synchronize data from the computing device, to another device.
     */
    SYNC_ERROR(PThemeIconContextName.STATUS, "sync-error"),

    /**
     * sync-synchronizing: The icon used while data is successfully synchronizing to another device.
     */
    SYNC_SYNCHRONIZING(PThemeIconContextName.STATUS, "sync-synchronizing"),

    /**
     * task-due: The icon used when a task is due soon.
     */
    TASK_DUE(PThemeIconContextName.STATUS, "task-due"),

    /**
     * task-past-due: The icon used when a task that was due, has been left incomplete.
     */
    TASK_PAST_DUE(PThemeIconContextName.STATUS, "task-past-due"),

    /**
     * user-available: The icon used when a user on a chat network is available to initiate a conversation with.
     */
    USER_AVAILABLE(PThemeIconContextName.STATUS, "user-available"),

    /**
     * user-away: The icon used when a user on a chat network is away from their keyboard and the chat program.
     */
    USER_AWAY(PThemeIconContextName.STATUS, "user-away"),

    /**
     * user-idle: The icon used when a user on a chat network has not been an active participant in any chats on the network, for an extended period of time.
     */
    USER_IDLE(PThemeIconContextName.STATUS, "user-idle"),

    /**
     * user-offline: The icon used when a user on a chat network is not available.
     */
    USER_OFFLINE(PThemeIconContextName.STATUS, "user-offline"),

    /**
     * user-trash-full: The icon for the user's “Trash” in the desktop's file manager, when there are items in the “Trash” waiting for disposal or recovery.
     */
    USER_TRASH_FULL(PThemeIconContextName.STATUS, "user-trash-full"),

    /**
     * weather-clear: The icon used while the weather for a region is “clear skies”.
     */
    WEATHER_CLEAR(PThemeIconContextName.STATUS, "weather-clear"),

    /**
     * weather-clear-night: The icon used while the weather for a region is “clear skies” during the night.
     */
    WEATHER_CLEAR_NIGHT(PThemeIconContextName.STATUS, "weather-clear-night"),

    /**
     * weather-few-clouds: The icon used while the weather for a region is “partly cloudy”.
     */
    WEATHER_FEW_CLOUDS(PThemeIconContextName.STATUS, "weather-few-clouds"),

    /**
     * weather-few-clouds-night: The icon used while the weather for a region is “partly cloudy” during the night.
     */
    WEATHER_FEW_CLOUDS_NIGHT(PThemeIconContextName.STATUS, "weather-few-clouds-night"),

    /**
     * weather-fog: The icon used while the weather for a region is “foggy”.
     */
    WEATHER_FOG(PThemeIconContextName.STATUS, "weather-fog"),

    /**
     * weather-overcast: The icon used while the weather for a region is “overcast”.
     */
    WEATHER_OVERCAST(PThemeIconContextName.STATUS, "weather-overcast"),

    /**
     * weather-severe-alert: The icon used while a sever weather alert is in effect for a region.
     */
    WEATHER_SEVERE_ALERT(PThemeIconContextName.STATUS, "weather-severe-alert"),

    /**
     * weather-showers: The icon used while rain showers are occurring in a region.
     */
    WEATHER_SHOWERS(PThemeIconContextName.STATUS, "weather-showers"),

    /**
     * weather-showers-scattered: The icon used while scattered rain showers are occurring in a region.
     */
    WEATHER_SHOWERS_SCATTERED(PThemeIconContextName.STATUS, "weather-showers-scattered"),

    /**
     * weather-snow: The icon used while snow showers are occurring in a region.
     */
    WEATHER_SNOW(PThemeIconContextName.STATUS, "weather-snow"),

    /**
     * weather-storm: The icon used while storms are occurring in a region.
     */
    WEATHER_STORM(PThemeIconContextName.STATUS, "weather-storm"),
    ;
    
    /**
     * Category for this standard theme icon, e.g., {@link PThemeIconContextName#STATUS}.
     */
    public final PThemeIconContextName context;
    
    /**
     * Base filename for this standard theme icon, e.g., {@code "weather-storm"}.
     */
    public final String baseFileName;
    
    private PThemeIconName(PThemeIconContextName context, String baseFileName) {
        this.context = ObjectArgs.checkNotNull(context, "context");
        this.baseFileName = StringArgs.checkNotEmptyOrWhitespace(baseFileName, "baseFileName");
    }
    
    /**
     * Converts this reference to a debugger-friendly string.  Do not depend upon this method, nor
     * implicit {@link String} conversion.  Instead, if only the name is required, call
     * {@link #name()} directly.
     * <hr>
     * Docs from {@link Enum#toString()}:
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String contextStr =
            StringUtils.addPrefixPerLine(
                context.toString(),
                "\t",
                StringUtils.TextProcessorOption.SKIP_FIRST_LINE);
        String x = String.format(
            "enum %s: ["
            + "%n\tname(): '%s'"
            + "%n\tcontext: %s"
            + "%n\tbaseFileName: '%s'"
            + "%n\t]",
            PThemeIconName.class.getCanonicalName(),
            name(),
            contextStr,
            baseFileName);
        return x;
    }
}
