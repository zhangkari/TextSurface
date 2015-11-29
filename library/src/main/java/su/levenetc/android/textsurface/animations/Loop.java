package su.levenetc.android.textsurface.animations;

import su.levenetc.android.textsurface.interfaces.IEndListener;
import su.levenetc.android.textsurface.interfaces.ISurfaceAnimation;

/**
 * Created by ogaclejapan.
 */
public class Loop extends Sequential {

  private final IEndListener restartListener = new IEndListener() {
    @Override public void onAnimationEnd(ISurfaceAnimation animation) {
      if (canceled) {
        if (listener != null) listener.onAnimationEnd(Loop.this);
      } else {
        Loop.super.start(restartListener);
      }
    }
  };

  private IEndListener listener;
  private boolean canceled;

  @Override public void start(IEndListener listener) {
    this.listener = listener;
    this.canceled = false;
    super.start(restartListener);
  }

  @Override public void cancel() {
    canceled = true;
    super.cancel();
  }
}
