/* ************************************************************************
LEBAH PORTAL FRAMEWORK, http://lebah.sf.net
Copyright (C) 2007  Shamsul Bahrin








MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

* ************************************************************************ */

package com.mmdis.dis.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mmdis.dis.jpa.Persistence;


public final class ContextListener implements ServletContextListener, ServletContextAttributeListener {


	public ContextListener() { }
	
	

	public void contextInitialized(ServletContextEvent event) {
		ServletContext ctx = event.getServletContext();
		System.out.println("Inializing Tomcat Context: " + ctx.getServerInfo());
    	Persistence.db();
	}
	
	public void contextDestroyed(ServletContextEvent event)  {
		ServletContext ctx = event.getServletContext();
    	System.out.println("=========================");
		System.out.println("Destroying Tomcat Context: " + ctx.getServerInfo());
		Persistence.db().close();
		
	}
	
	//Notification that a new attribute was added to the servlet context. 
	public void attributeAdded(ServletContextAttributeEvent event) {
		//System.out.println("a new attribute was added.");
	}
          
	//Notification that an existing attribute has been remved from the servlet context. 
	public void attributeRemoved(ServletContextAttributeEvent event) {
		//System.out.println("an attribute was removed.");
	}          
	
	//Notification that an attribute on the servlet context has been replaced. 
	public void attributeReplaced(ServletContextAttributeEvent event) {
		//System.out.println("An attribute has been replaced.");
	}
	

          
	
}
