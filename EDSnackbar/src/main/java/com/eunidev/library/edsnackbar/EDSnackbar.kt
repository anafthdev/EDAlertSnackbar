package com.eunidev.library.edsnackbar

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

public class EDSnackbar {
	
	lateinit var snackbar: Snackbar
	
	private lateinit var context: Context
	private lateinit var snackbarLayout: Snackbar.SnackbarLayout
	private lateinit var view: View
	
	private lateinit var container: RelativeLayout
	
	/** Snackbar Action Button **/
	private lateinit var actionButton: Button
	
	/** Snackbar TextView Message **/
	private lateinit var tvMessage: TextView
	
	/** Snackbar Alert Icon {Success, Warning, Info, Failed} **/
	private lateinit var imageIcon: ImageView
	
	private var snackbarMessage = ""
	private var snackbarIconType = ""
	private var length = 0
	private var snackbarType = 0
	
	companion object {
		
		const val ICON_FILLED = "edlibrary:edsnackbar:icon_filled"
		const val ICON_OUTLINED = "edlibrary:edsnackbar:icon_outlined"
		
		private const val SNACKBAR_TYPE_SUCCESS = 13
		private const val SNACKBAR_TYPE_FAILED = 123
		private const val SNACKBAR_TYPE_WARNIG = 213
		private const val SNACKBAR_TYPE_INFO = 32
		
		fun success(context: Context, view: View, msg: String, length: Int, iconType: String = ICON_OUTLINED): EDSnackbar {
			return EDSnackbar().create(SNACKBAR_TYPE_SUCCESS, context, view, msg, length, iconType)
		}
		
		fun warning(context: Context, view: View, msg: String, length: Int, iconType: String = ICON_OUTLINED): EDSnackbar {
			return EDSnackbar().create(SNACKBAR_TYPE_WARNIG, context, view, msg, length, iconType)
		}
		
		fun info(context: Context, view: View, msg: String, length: Int, iconType: String = ICON_OUTLINED): EDSnackbar {
			return EDSnackbar().create(SNACKBAR_TYPE_INFO, context, view, msg, length, iconType)
		}
		
		fun failed(context: Context, view: View, msg: String, length: Int, iconType: String = ICON_OUTLINED): EDSnackbar {
			return EDSnackbar().create(SNACKBAR_TYPE_FAILED, context, view, msg, length, iconType)
		}
	}
	
	@SuppressLint("ShowToast")
	private fun create(type: Int, context: Context, v: View, s: String, i: Int, iconType: String): EDSnackbar {
		this.context = context
		this.view = v
		this.snackbarMessage = s
		this.snackbarIconType = iconType
		this.length = i
		this.snackbarType = type
		this.snackbar = Snackbar.make(view, snackbarMessage, length)
		this.snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
		
		build(type)
		
		return this
	}
	
	@SuppressLint("InflateParams")
	private fun build(type: Int) {
		
		/** Inflate the Layout */
		val layoutView = LayoutInflater.from(context).inflate(R.layout.snackbar_layout, null, false)
		snackbarLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).visibility = View.INVISIBLE
		this.container = layoutView.findViewById(R.id.container_SnackbarLayout)
		this.actionButton = layoutView.findViewById(R.id.buttonAction_SnackbarLayout)
		this.tvMessage = layoutView.findViewById(R.id.tvMessage_SnackbarLayout)
		this.imageIcon = layoutView.findViewById(R.id.imageSnackbar_SnackbarLayout)
		
		tvMessage.text = snackbarMessage
		
		when (type) {
			SNACKBAR_TYPE_SUCCESS -> {
				container.setBackgroundColor(ContextCompat.getColor(context, R.color.success))
				if (snackbarIconType == ICON_OUTLINED) imageIcon.setImageResource(R.drawable.ic_success_outlined)
				else imageIcon.setImageResource(R.drawable.ic_success_filled)
			}
			SNACKBAR_TYPE_FAILED -> {
				container.setBackgroundColor(ContextCompat.getColor(context, R.color.failed))
				if (snackbarIconType == ICON_OUTLINED) imageIcon.setImageResource(R.drawable.ic_failed_outlined)
				else imageIcon.setImageResource(R.drawable.ic_failed_filled)
			}
			SNACKBAR_TYPE_INFO -> {
				container.setBackgroundColor(ContextCompat.getColor(context, R.color.info))
				if (snackbarIconType == ICON_OUTLINED) imageIcon.setImageResource(R.drawable.ic_info_outlined)
				else imageIcon.setImageResource(R.drawable.ic_info_filled)
			}
			SNACKBAR_TYPE_WARNIG -> {
				container.setBackgroundColor(ContextCompat.getColor(context, R.color.warning))
				if (snackbarIconType == ICON_OUTLINED) imageIcon.setImageResource(R.drawable.ic_warning_outlined)
				else imageIcon.setImageResource(R.drawable.ic_warning_filled)
			}
		}
		
		snackbarLayout.setPadding(0,0,0,0)
		snackbarLayout.addView(layoutView, 0)
	}
	
	fun setAction(text: String, listener: View.OnClickListener) {
		actionButton.visibility = View.VISIBLE
		actionButton.text = text
		actionButton.setOnClickListener(listener)
	}
	
	/** Add Callback To Snackbar **/
	fun addCallback(callback: BaseTransientBottomBar.BaseCallback<Snackbar>) = snackbar.addCallback(callback)
	
	/** Remove Callback From Snackbar **/
	fun removeCallback(callback: BaseTransientBottomBar.BaseCallback<Snackbar>) = snackbar.removeCallback(callback)
	
	/** Enable or Disable Snackbar Alert Icon **/
	fun iconEnabled(enable: Boolean) = if (enable) imageIcon.visibility = View.VISIBLE else imageIcon.visibility = View.GONE
	
	/** Set Snackbar Message Text Color **/
	fun setMessageTextColor(color: Int) = actionButton.setTextColor(color)
	
	/** Set Snackbar Action Text Color **/
	fun setActionTextColor(color: Int) = actionButton.setTextColor(color)
	
	/** Set Snackbar Alert Icon **/
	fun setAlertIcon(@DrawableRes resId: Int) = imageIcon.setImageResource(resId)
	
	/** Show Snackbar **/
	fun show() = snackbar.show()
}