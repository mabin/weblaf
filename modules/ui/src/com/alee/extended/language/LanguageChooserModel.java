/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.extended.language;

import com.alee.laf.combobox.WebComboBoxModel;
import com.alee.managers.language.*;
import com.alee.managers.language.data.Dictionary;
import com.alee.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Custom model for {@link LanguageChooser}.
 *
 * @author Mikle Garin
 */

public class LanguageChooserModel extends WebComboBoxModel<Locale> implements LanguageListener, DictionaryListener
{
    /**
     * {@link List} of {@link Locale}s to limit choice to.
     * Although only {@link Locale}s that are {@link LanguageManager#isSuportedLocale(Locale)} will be used.
     * In case this list is not specified full list of {@link LanguageManager#getSupportedLocales()} will be used.
     */
    protected List<Locale> locales;

    /**
     * Constructs new {@link LanguageChooserModel}.
     */
    public LanguageChooserModel ()
    {
        super ();
        updateLocales ();
    }

    /**
     * Returns {@link List} of {@link Locale}s to choice is limited to.
     *
     * @return {@link List} of {@link Locale}s to choice is limited to
     */
    public List<Locale> getLocales ()
    {
        return locales;
    }

    /**
     * Sets {@link List} of {@link Locale}s to limit choice to.
     *
     * @param locales new {@link List} of {@link Locale}s to limit choice to
     */
    public void setLocales ( final List<Locale> locales )
    {
        this.locales = locales;
        updateLocales ();
    }

    /**
     * Updates model data according to currently supported and default global locales.
     */
    protected void updateLocales ()
    {
        final List<Locale> data;
        if ( CollectionUtils.notEmpty ( locales ) )
        {
            // If locales are limited we use all supported ones from the limitation list
            data = LanguageManager.getSupportedLocales ( locales );
        }
        else
        {
            // If locales are not explicitly limited we use all supported ones
            data = new ArrayList<Locale> ( LanguageManager.getSupportedLocales () );

            // We also try to add default system locale into the list if it is not there yet
            // This will help us to avoid confusion of supported system local being initially selected while not in the options list
            final Locale systemLocale = LanguageUtils.getSystemLocale ();
            if ( LanguageManager.isSuportedLocale ( systemLocale ) && !data.contains ( systemLocale ) )
            {
                data.add ( 0, systemLocale );
            }
        }
        replaceAllElements ( data );

        // Selected locale will always be selected in LanguageChooser as well
        // Even if it is not included into limitation list or it is not even supported
        // That is why you have to handle initialy selected language properly within your application
        setSelectedItem ( LanguageManager.getLocale () );
    }

    @Override
    public void languageChanged ( final Language oldLanguage, final Language newLanguage )
    {
        updateLocales ();
    }

    @Override
    public void dictionaryAdded ( final Dictionary dictionary )
    {
        updateLocales ();
    }

    @Override
    public void dictionaryRemoved ( final Dictionary dictionary )
    {
        updateLocales ();
    }

    @Override
    public void dictionariesCleared ()
    {
        updateLocales ();
    }
}