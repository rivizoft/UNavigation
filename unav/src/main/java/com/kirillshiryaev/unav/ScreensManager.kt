package com.kirillshiryaev.unav

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class ScreensManager(private val fragmentManager: FragmentManager) {

    private val _fragmentsMap: HashMap<KClass<*>, Fragment?> = hashMapOf()
    private val _chain: HashMap<KClass<*>, Fragment> = hashMapOf()

    fun <T : Screen> add(screen: KClass<out T>) {
        _fragmentsMap[screen] = null
    }

    fun <T : Screen> delete(screen: KClass<out T>) {
        _fragmentsMap.remove(screen)
    }

    inner class Router(private val containerViewId: Int) {

        private var currentFragment: Fragment? = null

        private fun throwExceptionIfScreenNotExistInMap(screen: KClass<*>) {
            if (!_fragmentsMap.containsKey(screen))
                throw Exception("Destination $screen not found in screens manager")
        }

        private fun getAndCacheFragment(screen: KClass<*>): Fragment {
            var fragment = _fragmentsMap[screen]

            if (fragment == null) {
                fragment = screen.createInstance() as Fragment
                _fragmentsMap[screen] = fragment
            }

            return fragment
        }

        private fun getFragmentWithoutCache(screen: KClass<*>): Fragment =
            screen.createInstance() as Fragment

        fun <T : Screen> navigateTo(screen: KClass<out T>, inChain: Boolean = false, args: Bundle? = null) {
            throwExceptionIfScreenNotExistInMap(screen)

            val fragment = getAndCacheFragment(screen)

            fragment.arguments = args

            fragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .addToBackStack(null)
                .commit()

            if (inChain) _chain[screen] = fragment
        }

        fun <T : Screen> navigateToWithoutSaveState(screen: KClass<out T>, inChain: Boolean = false, args: Bundle? = null) {
            throwExceptionIfScreenNotExistInMap(screen)

            val fragment = getFragmentWithoutCache(screen)

            fragment.arguments = args

            fragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .addToBackStack(null)
                .commit()

            if (inChain) _chain[screen] = fragment
        }

        fun <T : Screen> setStartScreen(screen: KClass<out T>) {
            throwExceptionIfScreenNotExistInMap(screen)

            val fragment = getAndCacheFragment(screen)

            fragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit()
        }

        fun clearScreensStack() {
            if (fragmentManager.backStackEntryCount > 0)
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        fun <T : Screen> clearCache(screen: KClass<out T>) {
            throwExceptionIfScreenNotExistInMap(screen)
            _fragmentsMap[screen] = null
        }

        fun clearAllCache() {
            for (screen in _fragmentsMap)
                screen.setValue(null)
        }

        fun <T : Screen> removeScreenFromChain(screen: KClass<out T>): Boolean {
            throwExceptionIfScreenNotExistInMap(screen)

            return if (!_chain.containsKey(screen))
                false
            else {
                fragmentManager.beginTransaction().remove(_chain[screen]!!).commit()
                _chain.remove(screen)
                true
            }
        }
    }

}