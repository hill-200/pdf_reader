package com.gwallaz.pdfreader.user_interface.navigation_drawer_actions

import android.content.Context
import android.content.Intent
import android.widget.Toast

class ShareApp (){


fun share(context: Context,textMessage: String){
   val shareTo = Intent().apply {
       action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, textMessage)
        type = "text/plain"
    }

    try {
      context.startActivity(shareTo)

   }catch (e: Exception){

       Toast.makeText(context,"Cannot send.Please try again!",Toast.LENGTH_SHORT).show()
    }
}
}