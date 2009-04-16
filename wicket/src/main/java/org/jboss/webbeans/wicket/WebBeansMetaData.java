package org.jboss.webbeans.wicket;

import org.apache.wicket.MetaDataKey;

/**
 * Public storage for the metadata key used by the WebBeans integration to store 
 * conversation ids in wicket page metadata.
 * @author cpopetz
 *
 */
public class WebBeansMetaData
{

   /**
    * This is the key we will use to to store the conversation metadata in the
    * wicket page.
    */
   public static final MetaDataKey CID = new MetaDataKey(String.class) {
      private static final long serialVersionUID = -8788010688731927318L; 
   }; 
}
